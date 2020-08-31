package akio.apps.newsreader.screen.listing;

import androidx.lifecycle.ViewModel;

import akio.apps.newsreader._di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(includes = {ListingModule.Bindings.class})
public abstract class ListingModule {

    @ContributesAndroidInjector
    abstract ListingFragment listingFragment();

    @Module
    interface Bindings {
        @Binds
        @IntoMap
        @ViewModelKey(ListingViewModel.class)
        ViewModel forecastListViewModel(ListingViewModel viewModel);
    }
}
