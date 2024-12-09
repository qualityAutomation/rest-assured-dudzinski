package model;

import java.util.Objects;

public class Post {

    private Integer id;
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) && Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
