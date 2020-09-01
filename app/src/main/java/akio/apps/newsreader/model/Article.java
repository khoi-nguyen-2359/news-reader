package akio.apps.newsreader.model;

public class Article {
    private final String title;

    private final String description;

    private final String link;

    private final String pubDateTime;

    private final String guid;

    public Article(String title, String description, String link, String pubDateTime, String guid) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDateTime = pubDateTime;
        this.guid = guid;
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

    public String getGuid() {
        return guid;
    }
}
