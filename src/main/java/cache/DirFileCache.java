package cache;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try {
            return new String(Files.readAllBytes(Paths.get(cachingDir, key)));
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке файла: " + e.getMessage());
            return null;
        }
    }
}