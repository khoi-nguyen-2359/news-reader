package akio.apps.newsreader.data.entity;

import javax.xml.bind.annotation.XmlElement;

public class Article {
    @XmlElement(name = "title")
    private final String title;

    @XmlElement(name = "description")
    private final String description;

    @XmlElement(name = "link")
    private final String link;

    @XmlElement(name = "pubDate")
    private final String pubDateTime;

    public Article() {
        this("", "", "", "");
    }

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
