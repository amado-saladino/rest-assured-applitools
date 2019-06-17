package tau;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpecMechanismTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    static void beforeClass() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
    }

    @ParameterizedTest
    @MethodSource("tau.Utilities#dataForZipCodePlace")
    void sendRequestWithSpec(String country, String zip, String place) {
        requestSpec.pathParam("country",country).pathParam("zip",zip)
                .basePath("{country}/{zip}");
        ValidatableResponse response = RequestCreator.sendGETRequest(requestSpec);
        response.body("places[0].'place name'",equalTo(place))
                .body("places.'place name'", hasItem(place))
                .body("places.'place name'", hasSize(1))
        .statusCode(200);
    }

    @ParameterizedTest
    @MethodSource("tau.Utilities#dataForZipCodePlace")
    void testPlaceNameIsValid(String countryCode, String zipCode, String placeName) {
        requestSpec.pathParam("country",countryCode)
                .pathParam("zip",zipCode)
                .basePath("{country}/{zip}");
        given().spec(requestSpec)
                .when().get()
                .then().spec(responseSpec)
                .body("places[0].'place name'",equalTo(placeName))
                .body("places.'place name'",hasItem(placeName))
                .body("places.'place name'",hasSize(1));
    }

    @ParameterizedTest
    @MethodSource("tau.Utilities#dataForZipCodePlace")
    void extractCheckpoints(String countryCode, String zipCode, String placeName) {
        ValidatableResponse response = given().spec(requestSpec)
                .pathParam("country",countryCode).pathParam("zipcode",zipCode)
                .when().get("{country}/{zipcode}")
                .then().spec(responseSpec);

        String actual_place_name = response.extract().path("places[0].'place name'");
        //Junit Assertion
        Assertions.assertEquals(placeName,actual_place_name);
        //REST Assured Assertion
        response.body("places.'place name'",hasItem(placeName));
        response.body("places.'place name'",hasSize(1));
    }

    @Test
    void returnManyPlacesByZipCode() {
        requestSpec.pathParams("country","CH","zip","1000").basePath("{country}/{zip}");
        ValidatableResponse response= RequestCreator.sendGETRequest(requestSpec);
        response.body("places",hasSize(4));
        response.statusCode(200);
        System.out.println( response.extract().asString() );
    }
}
