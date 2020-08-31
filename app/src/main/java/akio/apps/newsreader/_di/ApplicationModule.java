package akio.apps.newsreader._di;

import javax.inject.Named;
import javax.inject.Singleton;

import akio.apps.newsreader.BuildConfig;
import akio.apps.newsreader.data.VneRssApi;
import dagger.Module;
import dagger.Provides;
import kotlin.jvm.JvmStatic;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Module
public class ApplicationModule {
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
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static VneRssApi vneRssApi(Retrofit retrofit) {
        return retrofit.create(VneRssApi.class);
    }

    public static final String NAMED_VNE_RSS_BASE_URL = "NAMED_VNE_RSS_BASE_URL";

    public static final String VNE_RSS_BASE_URL = "https://vnexpress.net/rss/";
}
