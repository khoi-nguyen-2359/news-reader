package akio.apps.newsreader.data.article;

import akio.apps.newsreader.data.article.entity.FeedEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VneRssApi {
    @GET("tin-moi-nhat.rss")
    Call<FeedEntity> getLatestNews();
}
