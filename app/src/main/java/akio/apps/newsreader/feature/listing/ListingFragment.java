package akio.apps.newsreader.feature.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import akio.apps.newsreader.databinding.FragmentListingBinding;
import akio.apps.newsreader.model.Article;
import akio.apps.newsreader.model.Event;
import akio.apps.newsreader.model.EventObserver;
import akio.apps.newsreader.feature.BaseFragment;

public class ListingFragment extends BaseFragment {

    private ListingViewModel listingViewModel;

    private FragmentListingBinding viewBinding;
    private ArticleListAdapter listingAdapter;

    private Observer<? super Boolean> isInProgressObserver = (Observer<Boolean>) isInProgress -> {
        viewBinding.listingSwipeLayout.setRefreshing(isInProgress);
    };

    private Observer<? super Event<Throwable>> errorObserver = new EventObserver<>(throwable -> {
        new AlertDialog.Builder(requireActivity())
                .setMessage(throwable.getMessage())
                .show();
    });

    @Nullable
    public ListingEventListener listingEventListener;

    private SwipeRefreshLayout.OnRefreshListener swipeRefreshListener = () -> {
        listingViewModel.reloadFeed();
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentListingBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    protected void initDependencies() {
        listingViewModel = createViewModel(ListingViewModel.class);
    }

    protected void initViews() {
        listingAdapter = new ArticleListAdapter(listingEventListener);
        viewBinding.listingArticlesRecyclerView.setAdapter(listingAdapter);
        viewBinding.listingSwipeLayout.setOnRefreshListener(swipeRefreshListener);
    }

    protected void initObservers() {
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
