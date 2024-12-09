import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class VerifyResponse {

    @Test
    public void getPostByBody() {

        String expected = "{\n" +
                "  \"title\": \"Zaktualizowany post metodą PATCH\",\n" +
                "  \"author\": \"Author zaktualizowany post metodą PATCH\",\n" +
                "  \"id\": \"1\"\n" +
                "}";

        given().log().all().when().get("http://localhost:3000/posts/1").
                then().log().all().body(equalTo(expected));
    }

    @Test
    public void verifyByContainsBody() {

        given().log().all().when().get("http://localhost:3000/posts/1").
                then().log().all().body(Matchers.containsString("zaktualizowany"));
    }

    @Test
    public void verifyContainsBodyIgnoringCase() {

        given().log().all().when().get("http://localhost:3000/posts/1").
                then().log().all().body(Matchers.containsStringIgnoringCase("ZakTUAliZoWany"));
    }

    @Test
    public void checkSpecificField() {

        when().get("http://localhost:3000/posts/1").
                then().assertThat().log().all().body("title", equalTo("Zaktualizowany post metodą PATCH")).
                and().assertThat().body("author", equalTo("Author zaktualizowany post metodą PATCH"));
    }

    @Test
    public void getPostObject() {

        Integer id = 1;

        Post newPost = given().log().all().when().get("http://localhost:3000/posts/1").
                then().log().all().body("title", Matchers.equalTo("Zaktualizowany post metodą PATCH")).
                and().body("author", Matchers.equalTo("Author zaktualizowany post metodą PATCH")).extract().as(Post.class);

        Assert.assertEquals(newPost.getAuthor(), "Author zaktualizowany post metodą PATCH");
        Assert.assertEquals(newPost.getTitle(), "Zaktualizowany post metodą PATCH");
        Assert.assertEquals(newPost.getId(), id);
    }

    @Test
    public void addPostFromObject() {

        //Porównanie obiektów

        Post newPost = new Post();
        newPost.setTitle("Tytuł obiektowy");
        newPost.setAuthor("Author obiektowy");

        Post createdPost = given().log().all().contentType(ContentType.JSON).body(newPost).
                when().post("http://localhost:3000/posts").
                then().log().all().extract().body().as(Post.class);

        //musi być public boolean equals(Object o) w clasie Post
        Assert.assertEquals(newPost, createdPost);
    }
}
