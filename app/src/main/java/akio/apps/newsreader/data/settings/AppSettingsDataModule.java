package akio.apps.newsreader.data.settings;

import dagger.Binds;
import dagger.Module;

@Module(includes = {AppSettingsDataModule.Bindings.class})
public interface AppSettingsDataModule {
    @Module
    interface Bindings {
        @Binds
        AppSettingsStorage appSettingsStorage(PreferencesAppSettingsStorage storage);
    }
}
