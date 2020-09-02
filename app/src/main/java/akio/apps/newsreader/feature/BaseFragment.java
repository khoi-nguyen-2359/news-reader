package akio.apps.newsreader.feature;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ViewModelProvider viewModelProvider;

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

        initViews();
        initObservers();
    }

    protected abstract void initDependencies();
    protected abstract void initViews();
    protected abstract void initObservers();

    protected <T extends ViewModel> T createViewModel(Class<T> vmClass) {
        return viewModelProvider.get(vmClass);
    }
}
