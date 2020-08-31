package akio.apps.newsreader.data.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Channel {
    @XmlElement(name = "item", type = Article.class)
    private final List<Article> articles;

    public Channel() {
        this(new ArrayList<>());
    }

    public Channel(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
