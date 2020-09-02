package akio.apps.newsreader.feature.listing;

import androidx.lifecycle.ViewModel;

import akio.apps.newsreader._di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(includes = {ListingModule.Bindings.class})
public interface ListingModule {

    @ContributesAndroidInjector
    ListingFragment listingFragment();

    @Module
    interface Bindings {
        @Binds
        @IntoMap
        @ViewModelKey(ListingViewModel.class)
        ViewModel forecastListViewModel(ListingViewModelImpl viewModel);
    }
}
