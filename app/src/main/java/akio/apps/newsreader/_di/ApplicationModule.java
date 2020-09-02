package akio.apps.newsreader._di;

import androidx.lifecycle.ViewModelProvider;

import akio.apps.newsreader.feature.HomeActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ApplicationModule.Bindings.class})
public abstract class ApplicationModule {

//    @ContributesAndroidInjector
//    abstract HomeActivity mainActivity();

    @Module
    interface Bindings {
        @Binds
        ViewModelProvider.Factory viewModelFactory(ViewModelFactory factory);
    }

}
