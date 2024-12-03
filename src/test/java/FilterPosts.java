import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {

        //Pobierz tylko te posty, których autorem jest Daria

        given().log().all().queryParam("author", "Daria").
                when().get("http://localhost:3000/posts").
                then().log().all();
    }

    @Test
    public void filterPostsById() {

        //Pobierz tylko te posty, których id jest równe jeden

        given().queryParam("id", "1").
                when().get("http://localhost:3000/posts").
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void filterPostsByAuthorAndTitle() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Pierwszy post z pliku");
        params.put("author","Tomek");

        given().log().all().queryParams(params).
                when().get("http://localhost:3000/posts").
                then().log().all().statusLine(Matchers.containsString("OK"));
    }
}
