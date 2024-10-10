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
     * Убрал поле created так как оно содержит текущее время создания объекта,
     * соответственно оно по идее будет различаться для каждого экземпляра,
     * даже в случае того что если все остальные поля будут одинаковы.
     * */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(title, post.title)
                && Objects.equals(link, post.link)
                && Objects.equals(description, post.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, description);
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