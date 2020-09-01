package akio.apps.newsreader.feature.browser;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {BrowserModule.Bindings.class})
public abstract class BrowserModule {

    @ContributesAndroidInjector
    abstract BrowserFragment browserFragment();

    @Module
    interface Bindings {
    }
}
