import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyResponse {
    @Test
    public void getPost() {

        String expected = "{\n" +
                "  \"title\": \"Zaktualizowany post metodą PATCH\",\n" +
                "  \"author\": \"Author zaktualizowany post metodą PATCH\",\n" +
                "  \"id\": \"1\"\n" +
                "}";

        given().log().all().when().get("http://localhost:3000/posts/1").
                then().log().all().body(Matchers.equalTo(expected));
    }
}
