package grabber;

import model.Post;
import java.util.List;

public interface Parse {
    List<Post> list(String link);
}
