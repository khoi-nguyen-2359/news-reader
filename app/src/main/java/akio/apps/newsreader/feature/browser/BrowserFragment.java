package akio.apps.newsreader.feature.browser;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import akio.apps.newsreader.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    private static final String ARG_URL = "ARG_URL";

    public static BrowserFragment createInstance(String url) {
        BrowserFragment fragment = new BrowserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {
        String articleUrl = getArgUrl();

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                WebSettingsCompat.setForceDark(viewBinding.browserWebView.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
            } else {
                viewBinding.browserWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        applyDarkModeCss();
                    }
                });
            }
        }

        viewBinding.browserWebView.getSettings().setJavaScriptEnabled(true);
        viewBinding.browserWebView.loadUrl(articleUrl);
    }

    private void applyDarkModeCss() {
        String js = "var parent = document.getElementsByTagName('head').item(0);" +
                "var style = document.createElement('style');" +
                "style.type = 'text/css';" +
                "style.innerHTML = ':root{filter:invert(100%);-webkit-filter:invert(100%);}iframe,img,amp-img,svg{filter:invert(100%);-webkit-filter:invert(100%);}';" +
                "parent.appendChild(style)";

        viewBinding.browserWebView.evaluateJavascript(js, s -> {
            Log.d("browser", "evaluateJavascript done: " + s);
        });
    }
}
