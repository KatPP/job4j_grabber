package utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements DateTimeParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    @Override
    public OffsetDateTime parse(String parse) {
        String formattedDate = parse.trim();
        return OffsetDateTime.parse(formattedDate, INPUT_FORMATTER);
    }

    public String format(OffsetDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return dateTime.format(outputFormatter);
    }
}