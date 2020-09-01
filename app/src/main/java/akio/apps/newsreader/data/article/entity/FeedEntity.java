package akio.apps.newsreader.data.article.entity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class FeedEntity {
    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<ArticleEntity> articles;

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}
