package akio.apps.newsreader.data.article;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import akio.apps.newsreader.TestHelper;
import akio.apps.newsreader.data.article.entity.Article;
import akio.apps.newsreader.data.article.entity.Channel;
import akio.apps.newsreader.data.article.entity.Feed;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VneRssApiTest {


    VneRssApi vneRssApi;
    MockWebServer mockServer;

    @Before
    public void setup() {
        mockServer = new MockWebServer();

        OkHttpClient.Builder builder = ArticleDataModule.okHttpClientBuilder();
        Retrofit retrofit = ArticleDataModule.vneRssRetrofit(builder, mockServer.url("/").toString());
        vneRssApi = ArticleDataModule.vneRssApi(retrofit);
    }

    @After
    public void tearDown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testRetrofitApiModelParsing() throws IOException {
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("rss_sample.xml");
        String responseString = TestHelper.readTextStream(resourceStream);
        resourceStream.close();
        mockServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(responseString)
        );

        Call<Feed> call = vneRssApi.getLatestNews();
        Response<Feed> response = call.execute();
        Feed feed = response.body();
        assertNotNull(feed);
        Channel channel = feed.getChannel();
        assertNotNull(channel);
        List<Article> articles = channel.getArticles();
        assertNotNull(articles);
        assertEquals(2, articles.size());
        assertEquals("Thêm một người nhập viện do ngộ độc pate Minh Chay", articles.get(0).getTitle());
        assertEquals("Thu giữ kho hàng nghi nhập lậu từ Trung Quốc", articles.get(1).getTitle());
    }
}
