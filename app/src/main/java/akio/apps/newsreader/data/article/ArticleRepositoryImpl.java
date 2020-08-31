package akio.apps.newsreader.data.article;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.entity.ArticleEntity;
import akio.apps.newsreader.data.article.entity.FeedEntity;
import akio.apps.newsreader.model.Resource;
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

        rssApi.getLatestNews().enqueue(new Callback<FeedEntity>() {
            @Override
            public void onResponse(Call<FeedEntity> call, Response<FeedEntity> response) {
                liveData.setValue(new Resource.Success<>(response.body().getChannel().getArticles()));
            }

            @Override
            public void onFailure(Call<FeedEntity> call, Throwable t) {
                liveData.setValue(new Resource.Error<>(t, null));
            }
        });

        return liveData;
    }
}
