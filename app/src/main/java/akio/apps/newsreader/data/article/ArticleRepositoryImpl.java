package akio.apps.newsreader.data.article;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.entity.Article;
import akio.apps.newsreader.data.article.entity.Feed;
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
    public LiveData<Resource<List<Article>>> getArticles() {
        MutableLiveData<Resource<List<Article>>> liveData = new MutableLiveData<>();

        rssApi.getLatestNews().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                liveData.setValue(new Resource.Success<>(response.body().getChannel().getArticles()));
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                liveData.setValue(new Resource.Error<>(t, null));
            }
        });

        return liveData;
    }
}
