package akio.apps.newsreader.data.article;

import androidx.arch.core.executor.DefaultTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Named;

import akio.apps.newsreader.data.Resource;
import akio.apps.newsreader.data.article.db.ArticleDao;
import akio.apps.newsreader.data.article.db.ArticleDatabase;
import akio.apps.newsreader.data.article.db.ArticleDto;
import akio.apps.newsreader.data.article.db.ArticleDtoMapper;
import akio.apps.newsreader.data.article.entity.ArticleEntity;
import akio.apps.newsreader.data.article.entity.ArticleEntityMapper;
import akio.apps.newsreader.data.article.entity.FeedEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static akio.apps.newsreader._di.ApplicationModule.NAMED_IO_EXECUTOR;

public class ArticleRepositoryImpl implements ArticleRepository {

    private final VneRssApi rssApi;
    private final ArticleDao articleDao;
    private ArticleEntityMapper articleEntityMapper;
    private ArticleDtoMapper articleDtoMapper;
    private ExecutorService executorService;

    @Inject
    public ArticleRepositoryImpl(VneRssApi rssApi, ArticleDatabase articleDatabase,
                                 ArticleEntityMapper articleEntityMapper,
                                 ArticleDtoMapper articleDtoMapper,
                                 @Named(NAMED_IO_EXECUTOR) ExecutorService executorService) {
        this.rssApi = rssApi;
        this.articleDao = articleDatabase.articleDao();
        this.articleEntityMapper = articleEntityMapper;
        this.articleDtoMapper = articleDtoMapper;
        this.executorService = executorService;
    }

    @Override
    public LiveData<Resource<List<ArticleEntity>>> getArticles() {
        MediatorLiveData<Resource<List<ArticleEntity>>> resultLiveData = new MediatorLiveData<>();
        LiveData<List<ArticleDto>> articlesSource = articleDao.getArticles();
        LiveData<Resource.Loading<List<ArticleEntity>>> loadingSource = Transformations.map(articlesSource, input -> new Resource.Loading<>(articleEntityMapper.map(input)));
        resultLiveData.addSource(loadingSource, resultLiveData::setValue);

        rssApi.getLatestNews().enqueue(new Callback<FeedEntity>() {
            @Override
            public void onResponse(@NotNull Call<FeedEntity> call, @NotNull Response<FeedEntity> response) {
                resultLiveData.removeSource(loadingSource);

                FeedEntity responseBody = response.body();
                if (responseBody == null) {
                    Throwable nullBodyException = new Exception("Article listing response body is null");
                    LiveData<Resource<List<ArticleEntity>>> errorSource = Transformations.map(articlesSource, input -> new Resource.Error<>(nullBodyException, articleEntityMapper.map(input)));
                    resultLiveData.addSource(errorSource, resultLiveData::setValue);
                } else {
                    LiveData<Resource<List<ArticleEntity>>> successSource = Transformations.map(articlesSource, input -> new Resource.Success<>(articleEntityMapper.map(input)));
                    resultLiveData.addSource(successSource, resultLiveData::setValue);
                    executorService.execute(() -> articleDao.replaceArticles(articleDtoMapper.map(responseBody.getArticles())));
                }
            }

            @Override
            public void onFailure(@NotNull Call<FeedEntity> call, @NotNull Throwable t) {
                resultLiveData.removeSource(loadingSource);

                LiveData<Resource<List<ArticleEntity>>> errorSource = Transformations.map(articlesSource, input -> new Resource.Error<>(t, articleEntityMapper.map(input)));
                resultLiveData.addSource(errorSource, resultLiveData::setValue);
            }
        });

        return resultLiveData;
    }
}
