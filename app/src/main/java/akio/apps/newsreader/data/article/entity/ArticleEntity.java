package akio.apps.newsreader.data.article.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class ArticleEntity {
    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Element(name = "link")
    private String link;

    @Element(name = "pubDate")
    private String pubDateTime;

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

    public void setPubDateTime(String pubDateTime) {
        this.pubDateTime = pubDateTime;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
