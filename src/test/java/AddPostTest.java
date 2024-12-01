import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddPostTest {

    @Test
    public void addPost() {
        String newPost = "{\n" +
                "  \"title\": \"Pierwszy post\",\n" +
                "  \"author\": \"Paweł\"\n" +
                "}";

        given().log().all().contentType(ContentType.JSON)
                .body(newPost)
                .when().post("http://localhost:3000/posts")
                .then()
                .log()
                .all();
    }

    @Test
    public void addPostFromFile() {
        File newPost = new File("src/test/resources/post.json");

        given().log().all().contentType(ContentType.JSON)
                .body(newPost)
                .when().post("http://localhost:3000/posts")
                .then()
                .log()
                .all();
    }

    @Test
    public void addPostFromMap() {
        // Zeby zadziałało dodawanie posta z mapy
        // trzeba dodać zależność o nazwie "jackson-databind"

        Map<String, Object> newPost = new HashMap<>();
        newPost.put("Title", "Tytuł z mapy");
        newPost.put("Author", "Daria");

        given().log().all().contentType(ContentType.JSON)
                .body(newPost)
                .when().post("http://localhost:3000/posts")
                .then()
                .log()
                .all();
    }

    @Test
    public void addPostFromObject(){
        Post newPost = new Post();
        newPost.setTitle("Tytuł obiektowy");
        newPost.setAuthor("Author obiektowy");

        given().log().all().contentType(ContentType.JSON)
                .body(newPost)
                .when().post("http://localhost:3000/posts")
                .then()
                .log()
                .all();
    }
}
