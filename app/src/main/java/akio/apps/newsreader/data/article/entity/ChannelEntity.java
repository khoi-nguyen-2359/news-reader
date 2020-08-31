package akio.apps.newsreader.data.article.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "channel", strict = false)
public class ChannelEntity {

    @ElementList(name = "item", inline = true)
    private List<ArticleEntity> articles;

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}
