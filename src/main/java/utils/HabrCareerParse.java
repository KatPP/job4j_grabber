package utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.OffsetDateTime;

public class HabrCareerParse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    private static final int PAGES_PARSE = 5;

    public static void main(String[] args) throws IOException {
        for (int pageNumber = 1; pageNumber <= PAGES_PARSE; pageNumber++) {
            String fullLink = "%s%s%d%s".formatted(SOURCE_LINK, PREFIX, pageNumber, SUFFIX);
            Connection connection = Jsoup.connect(fullLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            HabrCareerDateTimeParser dateTimeParser = new HabrCareerDateTimeParser();

            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element datetime = row.select(".vacancy-card__date").first();
                Element linkElement = titleElement.child(0);
                String date = datetime.child(0).attr("datetime");
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                OffsetDateTime offsetDateTime = dateTimeParser.parse(date);
                String formattedDate = dateTimeParser.format(offsetDateTime);

                System.out.printf("Вакансия: - %s. Дата публикации: - %s. Ссылка: - %s%n", vacancyName, formattedDate, link);
            });
        }
    }
}