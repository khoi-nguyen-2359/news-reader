package akio.apps.newsreader;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import javax.inject.Inject;

import akio.apps.newsreader._di.DaggerApplicationComponent;
import akio.apps.newsreader.util.DarkModeHelper;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class NewsReaderApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent.factory().create(this).inject(this);

        restoreApplicationTheme();
    }

    private void restoreApplicationTheme() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String darkModePrefKey = getString(R.string.application_preferences_dark_mode_key);
        DarkModeHelper.applyDarkModePreference(this, sharedPrefs, darkModePrefKey);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
