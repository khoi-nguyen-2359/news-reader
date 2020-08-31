package akio.apps.newsreader._di;

import javax.inject.Singleton;

import akio.apps.newsreader.NewsReaderApplication;
import akio.apps.newsreader.data.article.ArticleDataModule;
import akio.apps.newsreader.screen.listing.ListingModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        ArticleDataModule.class,
        ListingModule.class
})
public interface ApplicationComponent extends AndroidInjector<NewsReaderApplication> {
    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance NewsReaderApplication application);
    }
}
