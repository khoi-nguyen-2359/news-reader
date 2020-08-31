package akio.apps.newsreader.screen.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.databinding.FragmentListingBinding;
import akio.apps.newsreader.model.Article;
import dagger.android.support.AndroidSupportInjection;

public class ListingFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ViewModelProvider viewModelProvider;
    private ListingViewModel listingViewModel;

    private FragmentListingBinding viewBinding;
    private ArticleListAdapter listingAdapter;

    public ListingFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initDependencies();
        super.onCreate(savedInstanceState);
    }

    private void initDependencies() {
        AndroidSupportInjection.inject(this);
        viewModelProvider = new ViewModelProvider(this, viewModelFactory);
        listingViewModel = viewModelProvider.get(ListingViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentListingBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listingAdapter = new ArticleListAdapter();
        viewBinding.listingArticlesRecyclerView.setAdapter(listingAdapter);
        listingViewModel.getArticleList().observe(getViewLifecycleOwner(), articleListObserver);
    }

    private Observer<? super List<Article>> articleListObserver = (Observer<List<Article>>) articles -> {
        listingAdapter.submitList(articles);
    };

    public static ListingFragment createInstance() {
        return new ListingFragment();
    }
}
