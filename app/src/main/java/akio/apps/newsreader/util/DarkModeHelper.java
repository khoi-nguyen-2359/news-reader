package akio.apps.newsreader.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatDelegate;

import akio.apps.newsreader.R;

public class DarkModeHelper {
    public static void applyDarkModePreference(Context context, SharedPreferences sharedPreferences, String darkModePrefKey) {
        String darkModePrefValue = sharedPreferences.getString(darkModePrefKey, null);
        Resources resources = context.getResources();
        if (resources.getString(R.string.pref_value_dark_mode_night_system).equals(darkModePrefValue)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else if (resources.getString(R.string.pref_value_dark_mode_night_no).equals(darkModePrefValue)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (resources.getString(R.string.pref_value_dark_mode_night_yes).equals(darkModePrefValue)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}
