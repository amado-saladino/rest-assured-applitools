package tau;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestCreator {

    public static ValidatableResponse sendGETRequest(RequestSpecification reqSpec) {
        return given().spec(reqSpec).when().get().then();
    }

    public static ValidatableResponse sendPOSTRequest(RequestSpecification reqSpec) {
        return given().spec(reqSpec).when().post().then();
    }
}
