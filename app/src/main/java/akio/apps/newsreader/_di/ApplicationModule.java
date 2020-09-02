package akio.apps.newsreader._di;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import akio.apps.newsreader.NewsReaderApplication;
import akio.apps.newsreader.feature.HomeActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ApplicationModule.Bindings.class})
public abstract class ApplicationModule {

    @Module
    interface Bindings {
        @Binds
        ViewModelProvider.Factory viewModelFactory(ViewModelFactory factory);

        @Binds
        Context appContext(NewsReaderApplication application);
    }

}
