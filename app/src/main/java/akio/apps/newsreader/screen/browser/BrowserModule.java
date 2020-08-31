package akio.apps.newsreader.screen.browser;

import androidx.lifecycle.ViewModel;

import akio.apps.newsreader._di.ViewModelKey;
import akio.apps.newsreader.screen.listing.ListingFragment;
import akio.apps.newsreader.screen.listing.ListingViewModel;
import akio.apps.newsreader.screen.listing.ListingViewModelImpl;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(includes = {BrowserModule.Bindings.class})
public abstract class BrowserModule {

    @ContributesAndroidInjector
    abstract BrowserFragment browserFragment();

    @Module
    interface Bindings {
    }
}
