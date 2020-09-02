package akio.apps.newsreader.data.settings;

import androidx.lifecycle.LiveData;

public interface AppSettingsStorage {
    String getDarkModeValue();

    LiveData<String> getDarkMode();
}
