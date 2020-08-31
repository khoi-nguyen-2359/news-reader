package akio.apps.newsreader.screen.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import akio.apps.newsreader.databinding.FragmentListingBinding;
import akio.apps.newsreader.model.Article;
import akio.apps.newsreader.model.Event;
import akio.apps.newsreader.model.EventObserver;
import dagger.android.support.AndroidSupportInjection;

public class ListingFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ViewModelProvider viewModelProvider;
    private ListingViewModel listingViewModel;

    private FragmentListingBinding viewBinding;
    private ArticleListAdapter listingAdapter;

    private View.OnClickListener onArticleItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private Observer<? super Boolean> isInProgressObserver = (Observer<Boolean>) isInProgress -> {
        viewBinding.listingSwipeLayout.setRefreshing(isInProgress);
    };

    private Observer<? super Event<Throwable>> errorObserver = new EventObserver<>(throwable -> {
        new AlertDialog.Builder(requireActivity())
                .setMessage(throwable.getMessage())
                .show();
    });

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

        initViews();

        initObservers();
    }

    private void initViews() {
        listingAdapter = new ArticleListAdapter(onArticleItemClick);
        viewBinding.listingArticlesRecyclerView.setAdapter(listingAdapter);
        viewBinding.listingSwipeLayout.setEnabled(false);
    }

    private void initObservers() {
        listingViewModel.getArticleList().observe(getViewLifecycleOwner(), articleListObserver);
        listingViewModel.isInProgress().observe(getViewLifecycleOwner(), isInProgressObserver);
        listingViewModel.getError().observe(getViewLifecycleOwner(), errorObserver);
    }

    private Observer<? super List<Article>> articleListObserver = (Observer<List<Article>>) articles -> {
        listingAdapter.submitList(articles);
    };

    public static ListingFragment createInstance() {
        return new ListingFragment();
    }
}
