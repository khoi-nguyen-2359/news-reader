package akio.apps.newsreader.data.article;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Named;
import javax.inject.Singleton;

import akio.apps.newsreader.BuildConfig;
import akio.apps.newsreader.data.article.db.ArticleDatabase;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module(includes = {ArticleDataModule.Bindings.class})
public class ArticleDataModule {

    @Module
    interface Bindings {
        @Binds
        ArticleRepository articleRepository(ArticleRepositoryImpl repo);
    }

    @Provides
    @Singleton
    ArticleDatabase articleDatabase(Context appContext) {
        return Room.databaseBuilder(
                appContext,
                ArticleDatabase.class,
                "article_database"
        )
                .build();
    }

    @Provides
    public static OkHttpClient.Builder okHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.LOGGING_ENABLED) {
            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logger);
        }

        return builder;
    }

    @Provides
    @Singleton
    public static Retrofit vneRssRetrofit(OkHttpClient.Builder okHttpClientBuilder, @Named(NAMED_VNE_RSS_BASE_URL) String baseUrl) {
        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static VneRssApi vneRssApi(Retrofit retrofit) {
        return retrofit.create(VneRssApi.class);
    }

    @Provides
    @Named(NAMED_VNE_RSS_BASE_URL)
    public static String vneRssBaseUrl() {
        return VNE_RSS_BASE_URL;
    }

    public static final String NAMED_VNE_RSS_BASE_URL = "NAMED_VNE_RSS_BASE_URL";

    public static final String VNE_RSS_BASE_URL = "https://vnexpress.net/rss/";
}
