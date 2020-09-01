package akio.apps.newsreader.data.article;

import androidx.lifecycle.LiveData;

import java.util.List;

import akio.apps.newsreader.data.Resource;
import akio.apps.newsreader.data.article.entity.ArticleEntity;
import akio.apps.newsreader.model.Article;

public interface ArticleRepository {
    LiveData<Resource<List<ArticleEntity>>> getArticles();
}
