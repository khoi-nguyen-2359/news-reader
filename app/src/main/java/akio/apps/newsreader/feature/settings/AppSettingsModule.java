package akio.apps.newsreader.feature.settings;

import androidx.lifecycle.ViewModel;

import akio.apps.newsreader._di.ViewModelKey;
import akio.apps.newsreader.data.settings.AppSettingsStorage;
import akio.apps.newsreader.data.settings.PreferencesAppSettingsStorage;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(includes = {AppSettingsModule.Bindings.class})
public interface AppSettingsModule {

    @ContributesAndroidInjector
    AppSettingsFragment appSettingsFragment();

    @Module
    interface Bindings {
        @Binds
        @IntoMap
        @ViewModelKey(AppSettingsViewModel.class)
        ViewModel appSettingsViewModel(AppSettingsViewModelImpl viewModel);
    }
}
