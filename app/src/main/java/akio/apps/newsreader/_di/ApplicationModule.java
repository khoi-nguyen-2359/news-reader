package akio.apps.newsreader._di;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import akio.apps.newsreader.NewsReaderApplication;
import dagger.Binds;
import dagger.Module;

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
