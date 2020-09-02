package akio.apps.newsreader.data.article;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.entity.ArticleEntity;
import akio.apps.newsreader.data.article.entity.FeedEntity;
import akio.apps.newsreader.data.Resource;
import akio.apps.newsreader.model.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepositoryImpl implements ArticleRepository {

    private final VneRssApi rssApi;

    @Inject
    public ArticleRepositoryImpl(VneRssApi rssApi) {
        this.rssApi = rssApi;
    }

    @Override
    public LiveData<Resource<List<ArticleEntity>>> getArticles() {
        MutableLiveData<Resource<List<ArticleEntity>>> liveData = new MutableLiveData<>();
        liveData.setValue(new Resource.Loading<>(null));

        rssApi.getLatestNews().enqueue(new Callback<FeedEntity>() {
            @Override
            public void onResponse(@NotNull Call<FeedEntity> call, @NotNull Response<FeedEntity> response) {
                FeedEntity responseBody = response.body();
                if (responseBody == null) {
                    liveData.setValue(new Resource.Error<>(new Exception("Article listing response body is null"), null));
                } else {
                    liveData.setValue(new Resource.Success<>(responseBody.getArticles()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<FeedEntity> call, @NotNull Throwable t) {
                liveData.setValue(new Resource.Error<>(t, null));
            }
        });

        return liveData;
    }
}
