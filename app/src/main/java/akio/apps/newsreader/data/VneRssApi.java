package akio.apps.newsreader.data;

import akio.apps.newsreader.data.entity.Feed;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VneRssApi {
    @GET("tin-moi-nhat.rss")
    Call<Feed> getLatestNews();
}
