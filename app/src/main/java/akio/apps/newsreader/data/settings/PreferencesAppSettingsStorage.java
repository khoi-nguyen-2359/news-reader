package akio.apps.newsreader.data.settings;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import javax.inject.Inject;

import akio.apps.newsreader.R;

public class PreferencesAppSettingsStorage implements AppSettingsStorage {

    private SharedPreferences appSettingsPreferences;
    private String darkModePrefKey;
    private Context appContext;
    private MutableLiveData<String> darkMode = new MutableLiveData<>();

    private final SharedPreferences.OnSharedPreferenceChangeListener onAppSettingsPrerencesChanged = (sharedPreferences, key) -> {
        if (darkModePrefKey.equals(key)) {
            darkMode.setValue(getDarkModeValue());
        }
    };

    @Inject
    public PreferencesAppSettingsStorage(Context appContext) {
        appSettingsPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        darkModePrefKey = appContext.getString(R.string.application_preferences_dark_mode_key);
        this.appContext = appContext;

        appSettingsPreferences.registerOnSharedPreferenceChangeListener(onAppSettingsPrerencesChanged);
    }

    @Override
    public String getDarkModeValue() {
        return appSettingsPreferences.getString(darkModePrefKey, appContext.getString(R.string.pref_value_dark_mode_default));
    }

    @Override
    public LiveData<String> getDarkMode() {
        darkMode.setValue(getDarkModeValue());
        return darkMode;
    }
}
