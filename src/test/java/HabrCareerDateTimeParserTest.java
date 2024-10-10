import org.junit.jupiter.api.Test;
import utils.DateTimeParser;
import utils.HabrCareerDateTimeParser;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.format.DateTimeParseException;

class HabrCareerDateTimeParserTest {
    private final DateTimeParser parser = new HabrCareerDateTimeParser();

    @Test
    void whenParseValidDate() {
        String inputDate = "2024-09-18T11:03:12+03:00";
        OffsetDateTime expectedDateTime = OffsetDateTime.of(2024, 9, 18, 11, 3, 12, 0, ZoneOffset.ofHours(3));
        OffsetDateTime actualDateTime = parser.parse(inputDate);
        assertThat(actualDateTime).isEqualTo(expectedDateTime);
    }

    @Test
    void whenFormatValidDateTime() {
        OffsetDateTime dateTime = OffsetDateTime.of(2024, 9, 18, 11, 3, 12, 0, ZoneOffset.ofHours(3));
        String expectedFormattedDate = "2024-09-18T11:03:12";
        String actualFormattedDate = ((HabrCareerDateTimeParser) parser).format(dateTime);
        assertThat(actualFormattedDate).isEqualTo(expectedFormattedDate);
    }

    @Test
    void whenParseInvalidDate() {
        String inputDate = "Некорректная дата";
        try {
            parser.parse(inputDate);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DateTimeParseException.class);
        }
    }

    @Test
    void whenParseDateWithoutOffset() {
        String inputDate = "2024-09-18T11:03:12";
        OffsetDateTime expectedDateTime = OffsetDateTime.of(2024, 9, 18, 11, 3, 12, 0, ZoneOffset.UTC);
        OffsetDateTime actualDateTime = parser.parse(inputDate + "+00:00");
        assertThat(actualDateTime).isEqualTo(expectedDateTime);
    }

    @Test
    void whenParseDateWithDifferentOffset() {
        String inputDate = "2024-09-18T11:03:12-05:00";
        OffsetDateTime expectedDateTime = OffsetDateTime.of(2024, 9, 18, 11, 3, 12, 0, ZoneOffset.ofHours(-5));
        OffsetDateTime actualDateTime = parser.parse(inputDate);
        assertThat(actualDateTime).isEqualTo(expectedDateTime);
    }

    @Test
    void whenFormatDateWithOffset() {
        OffsetDateTime dateTime = OffsetDateTime.of(2024, 9, 18, 11, 3, 12, 0, ZoneOffset.ofHours(3));
        String expectedFormattedDate = "2024-09-18T11:03:12";
        String actualFormattedDate = ((HabrCareerDateTimeParser) parser).format(dateTime);
        assertThat(actualFormattedDate).isEqualTo(expectedFormattedDate);
    }
}