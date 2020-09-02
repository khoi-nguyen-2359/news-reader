package akio.apps.newsreader.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.entity.ArticleEntity;

public class ArticleMapper {

    @Inject
    public ArticleMapper() {
    }

    public Article map(ArticleEntity entity) {
        return new Article(entity.getTitle(), entity.getDescription(), entity.getLink(), entity.getPubDate(), entity.getGuid());
    }

    public List<Article> map(List<ArticleEntity> entityList) {
        List<Article> modelList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); ++i) {
            modelList.add(map(entityList.get(i)));
        }
        return modelList;
    }
}
