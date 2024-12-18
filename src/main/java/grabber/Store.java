package grabber;

import model.Post;
import java.util.List;

public interface Store extends AutoCloseable {
    void save(Post post);

    List<Post> getAll();

    Post findById(int id);

    void close() throws Exception;
}
