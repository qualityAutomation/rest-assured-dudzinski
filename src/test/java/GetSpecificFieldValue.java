import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSpecificFieldValue {

    @Test
    public void checkSpecificField() {

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        String author = response.path("author");

        Assert.assertEquals(author, "Author zaktualizowany post metodą PATCH");

        String author2 = RestAssured.get("http://localhost:3000/posts/1").path("author");
        Assert.assertEquals(author2, "Author zaktualizowany post metodą PATCH");
    }

    @Test
    public void checkSpecificFieldJsonPath() {

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        JsonPath jsonPath = new JsonPath(response.asString());

        String author = jsonPath.get("author");

        Assert.assertEquals(author, "Author zaktualizowany post metodą PATCH");

        String stringResponse = RestAssured.get("http://localhost:3000/posts/1").asString();
        String author2 = JsonPath.from(stringResponse).get("author");
        Assert.assertEquals(author2, "Author zaktualizowany post metodą PATCH");
    }
}
