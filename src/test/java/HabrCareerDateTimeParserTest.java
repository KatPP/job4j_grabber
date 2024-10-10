import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import utils.DateTimeParser;
import utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void whenParseFivePagesThenCorrectNumberOfVacancies() throws IOException {
        List<String> vacancies = new ArrayList<>();
        String sourceLink = "https://career.habr.com";
        String prefix = "/vacancies?page=";
        String suffix = "&q=Java%20developer&type=all";
        int pagesToParse = 5;

        for (int pageNumber = 1; pageNumber <= pagesToParse; pageNumber++) {
            String fullLink = String.format("%s%s%d%s", sourceLink, prefix, pageNumber, suffix);
            Document document = Jsoup.connect(fullLink).get();
            Elements rows = document.select(".vacancy-card__inner");

            for (Element row : rows) {
                Element titleElement = row.select(".vacancy-card__title").first();
                if (titleElement != null) {
                    String vacancyName = titleElement.text();
                    vacancies.add(vacancyName);
                }
            }
        }
        assertThat(vacancies).isNotEmpty();
        assertThat(vacancies.size()).isGreaterThan(0);
    }

    @Test
    void whenParseFirstPageThenCorrectVacanciesCount() throws IOException {
        List<String> vacancies = new ArrayList<>();
        String sourceLink = "https://career.habr.com";
        String prefix = "/vacancies?page=";
        String suffix = "&q=Java%20developer&type=all";

        String fullLink = String.format("%s%s%d%s", sourceLink, prefix, 1, suffix);
        Document document = Jsoup.connect(fullLink).get();
        Elements rows = document.select(".vacancy-card__inner");

        for (Element row : rows) {
            Element titleElement = row.select(".vacancy-card__title").first();
            if (titleElement != null) {
                String vacancyName = titleElement.text();
                vacancies.add(vacancyName);
            }
        }
        assertThat(vacancies).isNotEmpty();
        assertThat(vacancies.size()).isLessThanOrEqualTo(25);
    }
}