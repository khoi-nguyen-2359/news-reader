package akio.apps.newsreader.feature.listing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import akio.apps.newsreader.R;
import akio.apps.newsreader.databinding.FragmentListingBinding;
import akio.apps.newsreader.model.Article;
import akio.apps.newsreader.model.Event;
import akio.apps.newsreader.model.EventObserver;
import akio.apps.newsreader.feature.BaseFragment;

public class ListingFragment extends BaseFragment {

    public static ListingFragment createInstance() {
        return new ListingFragment();
    }

    private ListingViewModel listingViewModel;

    private FragmentListingBinding viewBinding;

    private ArticleListAdapter listingAdapter;

    @Nullable
    public ListingEventListener listingEventListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentListingBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listingEventListener = null;
    }

    protected void initDependencies() {
        listingViewModel = createViewModel(ListingViewModel.class);
    }

    protected void initViews() {
        listingAdapter = new ArticleListAdapter(listingEventListener);
        viewBinding.listingArticlesRecyclerView.setAdapter(listingAdapter);
        viewBinding.listingSwipeLayout.setOnRefreshListener(swipeRefreshListener);

        DividerItemDecoration dividerDecoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        dividerDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.listing_article_item_divider));
        viewBinding.listingArticlesRecyclerView.addItemDecoration(dividerDecoration);
    }

    protected void initObservers() {
        listingViewModel.getArticleList().observe(getViewLifecycleOwner(), articleListObserver);
        listingViewModel.isInProgress().observe(getViewLifecycleOwner(), isInProgressObserver);
        listingViewModel.getError().observe(getViewLifecycleOwner(), errorObserver);
    }

    private Observer<? super List<Article>> articleListObserver = (Observer<List<Article>>) articles -> {
        listingAdapter.submitList(articles, new AsyncListListener() {
            @Override
            public void onCurrentListChanged() {
                viewBinding.listingArticlesRecyclerView.smoothScrollToPosition(0);
            }
        });
    };

    private Observer<? super Boolean> isInProgressObserver = (Observer<Boolean>) isInProgress -> {
        viewBinding.listingSwipeLayout.setRefreshing(isInProgress);
    };

    private Observer<? super Event<Throwable>> errorObserver = new EventObserver<>(throwable -> {
        new AlertDialog.Builder(requireActivity())
                .setMessage(throwable.getMessage())
                .show();
    });

    private SwipeRefreshLayout.OnRefreshListener swipeRefreshListener = () -> {
        listingViewModel.reloadFeed();
    };
}
