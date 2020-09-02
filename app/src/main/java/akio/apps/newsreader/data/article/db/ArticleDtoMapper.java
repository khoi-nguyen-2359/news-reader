package akio.apps.newsreader.data.article.db;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.entity.ArticleEntity;

public class ArticleDtoMapper {

    @Inject
    public ArticleDtoMapper() {}

    public ArticleDto map(ArticleEntity entity) {
        return new ArticleDto(entity.getGuid(), entity.getTitle(), entity.getDescription(), entity.getPubDate(), entity.getLink());
    }

    public List<ArticleDto> map(List<ArticleEntity> entityList) {
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); ++i) {
            articleDtoList.add(map(entityList.get(i)));
        }

        return articleDtoList;
    }
}
