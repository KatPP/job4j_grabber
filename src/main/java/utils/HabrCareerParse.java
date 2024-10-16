package utils;

import grabber.Parse;
import model.Post;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    public static final int NUMBER_PAGES = 5;
    public DateTimeParser dateTimeParser;
    private List<Post> post = new ArrayList<>();

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-description__text");
        StringJoiner joined = new StringJoiner("\n");
        rows.forEach(row -> {
            for (int i = 0; i < row.childNodeSize(); i++) {
                String text = row.child(i).text();
                joined.add(text);
            }
        });
        return joined.toString();
    }

    @Override
    public List<Post> list(String link) {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        int id = 0;
        for (int pageNumber = 1; pageNumber <= NUMBER_PAGES; pageNumber++) {
            Connection connection = Jsoup.connect(link);
            try {
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                for (Element row : rows) {
                    Element titleElement = row.select(".vacancy-card__title").first();
                    Element linkElement = titleElement.child(0);
                    String vacancyName = titleElement.text();
                    String linkVacancy = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                    Element dateElement = row.select(".vacancy-card__date").first();
                    Element dateClass = dateElement.child(0);
                    String date = dateClass.attr("datetime");
                    String description = null;
                    try {
                        description = retrieveDescription(linkVacancy);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    post.add(new Post(id++, vacancyName, description, linkVacancy, habrCareerParse.dateTimeParser.parse(date).toLocalDateTime()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return post;
    }
}