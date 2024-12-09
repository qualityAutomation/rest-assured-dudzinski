import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class JsonPathTest {

    @Test
    public void checkSpecificFieldJsonPath(){
        Response response = RestAssured.get("http://localhost:3000/posts/1");

        System.out.println(response.asString());
    }
}
