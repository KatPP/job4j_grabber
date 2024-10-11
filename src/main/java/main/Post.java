package main;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private final int id;
    private final String title;
    private final String link;
    private final String description;
    private final LocalDateTime created;

    public Post(int id, String title, String link, String description) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = LocalDateTime.now();
    }

    /**
     * Убрал поля title, description и created из equals() и hashCode(),
     * так как для определения уникальности экземпляра достаточно id и link.
     * Это позволяет избежать ненужного сравнения полей, которые могут
     * меняться или не быть уникальными.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && Objects.equals(link, post.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}