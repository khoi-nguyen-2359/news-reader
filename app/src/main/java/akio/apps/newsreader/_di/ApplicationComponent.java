package akio.apps.newsreader._di;

import javax.inject.Singleton;

import akio.apps.newsreader.NewsReaderApplication;
import akio.apps.newsreader.data.article.ArticleDataModule;
import akio.apps.newsreader.data.settings.AppSettingsDataModule;
import akio.apps.newsreader.feature.listing.ListingModule;
import akio.apps.newsreader.feature.settings.AppSettingsModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,

        // data modules
        ArticleDataModule.class,
        AppSettingsDataModule.class,

        // feature modules
        ListingModule.class,
        AppSettingsModule.class
})
public interface ApplicationComponent extends AndroidInjector<NewsReaderApplication> {
    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance NewsReaderApplication application);
    }
}
