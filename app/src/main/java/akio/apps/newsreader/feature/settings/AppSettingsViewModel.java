package akio.apps.newsreader.feature.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public abstract class AppSettingsViewModel extends ViewModel {
    abstract LiveData<String> getDarkMode();
}
