package akio.apps.newsreader.feature.settings;

import androidx.lifecycle.LiveData;

import javax.inject.Inject;

import akio.apps.newsreader.data.settings.AppSettingsStorage;

public class AppSettingsViewModelImpl extends AppSettingsViewModel {

    private AppSettingsStorage appSettingsStorage;

    @Inject
    public AppSettingsViewModelImpl(AppSettingsStorage appSettingsStorage) {
        this.appSettingsStorage = appSettingsStorage;
    }

    @Override
    LiveData<String> getDarkMode() {
        return appSettingsStorage.getDarkMode();
    }
}
