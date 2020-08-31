package akio.apps.newsreader.screen.listing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.ArticleRepository;
import akio.apps.newsreader.data.article.entity.ArticleEntity;
import akio.apps.newsreader.model.Article;
import akio.apps.newsreader.model.ArticleMapper;
import akio.apps.newsreader.model.Event;
import akio.apps.newsreader.model.Resource;

public class ListingViewModelImpl extends ListingViewModel {

    private final MutableLiveData<List<Article>> articleList = new MutableLiveData<>();
    private final MutableLiveData<Event<Throwable>> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isInProgress = new MutableLiveData<>();

    private ArticleRepository articleRepository;
    private LiveData<Resource<List<ArticleEntity>>> articleListSource;
    private ArticleMapper articleMapper;

    @Inject
    public ListingViewModelImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;

        this.articleListSource = articleRepository.getArticles();
        this.articleListSource.observeForever(articleListObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        articleListSource.removeObserver(articleListObserver);
    }

    private final Observer<? super Resource<List<ArticleEntity>>> articleListObserver = (Observer<Resource<List<ArticleEntity>>>) listResource -> {
        if (listResource instanceof Resource.Success) {
            Resource.Success<List<ArticleEntity>> successList = (Resource.Success<List<ArticleEntity>>) listResource;
            articleList.setValue(articleMapper.map(successList.getData()));
        }
    };

    @Override
    LiveData<List<Article>> getArticleList() {
        return articleList;
    }

    @Override
    LiveData<Event<Throwable>> getError() {
        return error;
    }

    @Override
    LiveData<Boolean> isInProgress() {
        return isInProgress;
    }
}
