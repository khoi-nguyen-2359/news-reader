package akio.apps.newsreader.data.article;

import androidx.lifecycle.LiveData;

import akio.apps.newsreader.data.Resource;

public interface ArticleRepository {
    LiveData<Resource> getArticles();
}
