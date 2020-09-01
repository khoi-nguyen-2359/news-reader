package akio.apps.newsreader.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.HashMap;
import java.util.Map;

import akio.apps.newsreader.R;

public class DarkModeHelper {
    @Nullable
    private static Map<String, Integer> mapDarkModeValues;

    public static void applyDarkModePreference(Context context, SharedPreferences sharedPreferences, String darkModePrefKey) {
        initDarkModeValueMap(context);

        String darkModePrefValue = sharedPreferences.getString(darkModePrefKey, null);
        if (darkModePrefValue == null) {
            return;
        }

        Integer darkModeSettingValue = mapDarkModeValues.get(darkModePrefValue);
        if (darkModeSettingValue == null) {
            return;
        }

        AppCompatDelegate.setDefaultNightMode(darkModeSettingValue);
    }

    private static void initDarkModeValueMap(Context context) {
        if (mapDarkModeValues != null) {
            return;
        }

        mapDarkModeValues = new HashMap<>();
        mapDarkModeValues.put(context.getResources().getString(R.string.pref_value_dark_mode_night_system), AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        mapDarkModeValues.put(context.getResources().getString(R.string.pref_value_dark_mode_night_no), AppCompatDelegate.MODE_NIGHT_NO);
        mapDarkModeValues.put(context.getResources().getString(R.string.pref_value_dark_mode_night_yes), AppCompatDelegate.MODE_NIGHT_YES);
    }
}
