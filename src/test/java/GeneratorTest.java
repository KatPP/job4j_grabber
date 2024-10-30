import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    private final Generator generator = new Generator() {
        @Override
        public String produce(String template, Map<String, String> args) {
            return null;
        }
    };

    @Test
    public void whenTemplateHasMissingKeyThenThrowException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");

        generator.produce(template, args);
    }

    @Test
    public void whenArgsHasExtraKeyThenThrowException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("extraKey", "extraValue");

        generator.produce(template, args);
    }

    @Test
    public void whenTemplateIsEmptyThenThrowException() {
        String template = "";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");

        generator.produce(template, args);
    }

    @Test
    public void whenArgsIsEmptyThenThrowException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();

        generator.produce(template, args);
    }
}
