import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdatePost {

    @Test
    public void replacePostFromMap(){
        // Zeby zadziałało dodawanie posta z mapy
        // trzeba dodać zależność o nazwie "jackson-databind"

        // Metoda PUT podmienia nam całość zasobu, jeżeli podajemy wartośći to zostają one usunięte

        Map<String, Object> newPost = new HashMap<>();
        newPost.put("Title", "Tytuł po aktualizacji metodą PUT");
        newPost.put("Author", "Daria po aktualizacji metodą PUT");

        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().put("http://localhost:3000/posts/1").
                then().log().all();
    }

    @Test
    public void replacePostFromObject(){

        // Metoda PUT podmienia nam całość zasobu, jeżeli podajemy wartośći to zostają one usunięte

        Post newPost = new Post();
        newPost.setTitle("Zaktualizowany post metodą PUT");
        newPost.setAuthor("Author zaktualizowany post metodą PUT");

        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().put("http://localhost:3000/posts/1").
                then().log().all();
    }

    @Test
    public void updatePostFromObject(){

        // Metoda PATCH podmeni nam tylko część zasobu

        Post newPost = new Post();
        newPost.setTitle("Zaktualizowany post metodą PATCH");
        newPost.setAuthor("Author zaktualizowany post metodą PATCH");

        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().patch("http://localhost:3000/posts/1").
                then().log().all();
    }
}
