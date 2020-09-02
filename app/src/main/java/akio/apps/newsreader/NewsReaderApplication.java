package akio.apps.newsreader;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import javax.inject.Inject;

import akio.apps.newsreader._di.DaggerApplicationComponent;
import akio.apps.newsreader.data.settings.AppSettingsStorage;
import akio.apps.newsreader.util.DarkModeHelper;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class NewsReaderApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    AppSettingsStorage appSettingsStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent.factory().create(this).inject(this);

        DarkModeHelper.applyDarkMode(this, appSettingsStorage.getDarkModeValue());
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
