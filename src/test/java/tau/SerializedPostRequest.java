package tau;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tau.models.Post;

public class SerializedPostRequest {
    private static RequestSpecification requestSpec;

    @BeforeAll
    static void setupClass() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://localhost:3000").build();
    }

    @Test
    void addPost() {
        Post xPectedPost = new Post("Islam","Software Architecture");
        requestSpec.basePath("posts");
        requestSpec.body(xPectedPost).contentType(ContentType.JSON);
        ValidatableResponse response = RequestCreator.sendPOSTRequest(requestSpec);

        Post actualPost = response.extract().as(Post.class);
        Assertions.assertEquals(xPectedPost.getAuthor(),actualPost.getAuthor());
        Assertions.assertEquals(201,response.extract().statusCode());
    }
}
