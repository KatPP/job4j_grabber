package utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    private static final int PAGES_PARSE = 5;
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        for (int pageNumber = 1; pageNumber <= PAGES_PARSE; pageNumber++) {
            String fullLink = String.format("%s%s%d%s", SOURCE_LINK, PREFIX, pageNumber, SUFFIX);
            try {
                Connection connection = Jsoup.connect(fullLink);
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");

                for (Element row : rows) {
                    Element titleElement = row.select(".vacancy-card__title").first();
                    Element datetime = row.select(".vacancy-card__date").first();
                    Element linkElement = titleElement.child(0);

                    String date = datetime.child(0).attr("datetime");
                    String vacancyName = titleElement.text();
                    String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                    String description = retrieveDescription(vacancyLink);
                    OffsetDateTime offsetDateTime = dateTimeParser.parse(date);
                    String formattedDate = dateTimeParser.format(offsetDateTime);

                    Post post = new Post(vacancyName, vacancyLink, description, formattedDate);
                    posts.add(post);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    private String retrieveDescription(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Element descriptionElement = document.selectFirst(".vacancy-description__text");
            if (descriptionElement != null) {
                return descriptionElement.text();
            } else {
                return "Описание не найдено";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при загрузке описания";
        }
    }

    public static void main(String[] args) {
        DateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        HabrCareerParse habrCareerParse = new HabrCareerParse(dateTimeParser);
        List<Post> posts = habrCareerParse.list("https://career.habr.com/vacancies");
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}