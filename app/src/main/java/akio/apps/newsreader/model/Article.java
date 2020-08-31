package akio.apps.newsreader.model;

public class Article {
    private final String title;

    private final String description;

    private final String link;

    private final String pubDateTime;

    public Article(String title, String description, String link, String pubDateTime) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDateTime = pubDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getPubDateTime() {
        return pubDateTime;
    }

}
