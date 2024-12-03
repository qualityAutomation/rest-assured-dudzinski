import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetSpecificPostTest {

    @Test
    public void getPost() {
        when().get("http://localhost:3000/posts/1").
                then().log().all();

        //inny zapis, ten sam wynik
        when().get("http://localhost:3000/posts/{postId}", 1).
                then().log().all();
    }

    @Test
    public void getPostByPathParam() {
        given().pathParam("postId", 1).
                when().get("http://localhost:3000/posts/{postId}").
                then().log().all();
    }

}
