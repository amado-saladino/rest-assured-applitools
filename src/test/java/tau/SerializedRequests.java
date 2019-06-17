package tau;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tau.models.Location;

public class SerializedRequests {
    private static RequestSpecification requestSpec;

    @BeforeEach
    void beforeClass() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
    }

    @ParameterizedTest
    @MethodSource("tau.Utilities#dataForZipCodePlace")
    void getDeserializedLocations(String countryCode, String zipCode, String placeName) {
        requestSpec.pathParam("country",countryCode).pathParam("zip",zipCode)
                .basePath("{country}/{zip}");
        ValidatableResponse response = RequestCreator.sendGETRequest(requestSpec);
        Location location = response.extract().as(Location.class);

        //Junit Assertion
        Assertions.assertEquals(placeName,location.getPlaces().get(0).getPlaceName());
        Assertions.assertEquals(1,location.getPlaces().size());
        Assertions.assertEquals(countryCode.toUpperCase(), location.getCountryAbbreviation());
    }

    @Test
    void getLocationWithManyPlaces() {
        requestSpec.pathParam("country","CH")
                .pathParam("zipcode","1000")
                .basePath("{country}/{zipcode}");

        ValidatableResponse response = RequestCreator.sendGETRequest(requestSpec);
        Location location = response.extract().as(Location.class);
        Assertions.assertEquals(4,location.getPlaces().size());
        Assertions.assertEquals("CH",location.getCountryAbbreviation());
    }
}
