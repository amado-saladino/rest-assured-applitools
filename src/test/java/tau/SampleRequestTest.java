package tau;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SampleRequestTest {

    @Test
    void requestZipCode() {
        given().when().get("http://api.zippopotam.us/us/90210")
                .then()
                .body("places[0].'place name'",equalTo("Beverly Hills"))
                .body("places[0].state",equalTo("California"))
                .body("places.'place name'",hasItem("Beverly Hills"))
                .body("places.'place name'",hasSize(1));
    }

    @ParameterizedTest
    @MethodSource("tau.Utilities#dataForZipCodePlace")
    void checkCountryZippCodeMapToPlaceName(String countryCode, String zipCode, String placeName) {
        given().pathParam("country",countryCode).pathParam("zipcode",zipCode)
        .when().get("http://api.zippopotam.us/{country}/{zipcode}")
        .then().body("places[0].'place name'",equalTo(placeName))
        .body("places.'place name'",hasItem(placeName))
        .body("places.'place name'",hasSize(1));
    }

    @Test
    void logResponseBodyWithStatusCode() {
        given().log().body()
                .when().get("http://api.zippopotam.us/us/90210")
                .then().log().body()
                .statusCode(200);
    }

}
