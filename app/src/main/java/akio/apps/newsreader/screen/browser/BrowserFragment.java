package akio.apps.newsreader.screen.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import akio.apps.newsreader.databinding.FragmentBrowserBinding;
import akio.apps.newsreader.screen.BaseDiFragment;

public class BrowserFragment extends BaseDiFragment {

    private FragmentBrowserBinding viewBinding;

    private String getArgUrl() {
        return getArguments().getString(ARG_URL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentBrowserBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    protected void initDependencies() {

    }

    @Override
    protected void initViews() {
        viewBinding.browserWebView.getSettings().setJavaScriptEnabled(true);
        String url = getArgUrl();
        viewBinding.browserWebView.loadUrl(url);
    }

    @Override
    protected void initObservers() {

    }

    private static final String ARG_URL = "ARG_URL";

    public static BrowserFragment createInstance(String url) {
        BrowserFragment fragment = new BrowserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }
}
