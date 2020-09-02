package akio.apps.newsreader.data.article.entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.db.ArticleDto;

public class ArticleEntityMapper {

    @Inject
    public ArticleEntityMapper() {
    }

    public ArticleEntity map(ArticleDto articleDto) {
        return new ArticleEntity(articleDto.getGuid(), articleDto.getTitle(), articleDto.getDescription(), articleDto.getPubDate(), articleDto.getLink());
    }

    public List<ArticleEntity> map(List<ArticleDto> input) {
        List<ArticleEntity> result = new ArrayList<>();
        for (int i = 0; i < input.size(); ++i) {
            result.add(map(input.get(i)));
        }
        return result;
    }
}
