package akio.apps.newsreader.feature.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import akio.apps.newsreader.R;
import akio.apps.newsreader.util.DarkModeHelper;
import dagger.android.support.AndroidSupportInjection;

public class AppSettingsFragment extends PreferenceFragmentCompat {

    public static AppSettingsFragment createInstance() {
        return new AppSettingsFragment();
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ViewModelProvider viewModelProvider;

    private AppSettingsViewModel appSettingsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        viewModelProvider = new ViewModelProvider(this, viewModelFactory);
        initDependencies();

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initObservers();
    }

    protected void initDependencies() {
        appSettingsViewModel = viewModelProvider.get(AppSettingsViewModel.class);
    }

    protected void initObservers() {
        appSettingsViewModel.getDarkMode().observe(getViewLifecycleOwner(), darkModeObserver);
    }

    private Observer<? super String> darkModeObserver = (Observer<String>) darkModeValue -> {
        DarkModeHelper.applyDarkMode(getContext(), darkModeValue);
    };

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.application_preferences, rootKey);
    }
}
