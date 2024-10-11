package utils;

import java.time.OffsetDateTime;

public interface DateTimeParser {
    OffsetDateTime parse(String parse);

    String format(OffsetDateTime offsetDateTime);
}
