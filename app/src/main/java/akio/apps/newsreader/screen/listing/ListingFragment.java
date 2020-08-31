package akio.apps.newsreader.screen.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import akio.apps.newsreader.databinding.FragmentListingBinding;
import dagger.android.support.AndroidSupportInjection;

public class ListingFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ListingViewModel listingViewModel;

    private FragmentListingBinding viewBinding;
    private ViewModelProvider viewModelProvider;

    public ListingFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);

        viewModelProvider = new ViewModelProvider(this, viewModelFactory);
        listingViewModel = viewModelProvider.get(ListingViewModel.class);

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentListingBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    public static ListingFragment createInstance() {
        return new ListingFragment();
    }
}
