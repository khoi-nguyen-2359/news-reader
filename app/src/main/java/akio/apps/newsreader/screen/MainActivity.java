package akio.apps.newsreader.screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import akio.apps.newsreader.R;
import akio.apps.newsreader.databinding.ActivityMainBinding;
import akio.apps.newsreader.screen.browser.BrowserFragment;
import akio.apps.newsreader.screen.listing.ListingEventListener;
import akio.apps.newsreader.screen.listing.ListingFragment;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;

    private ListingEventListener listingEventListener = article -> {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, BrowserFragment.createInstance(article.getLink()))
                .addToBackStack(null)
                .commit();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, ListingFragment.createInstance())
                    .commit();
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof ListingFragment) {
            ListingFragment listingFragment = (ListingFragment) fragment;
            listingFragment.listingEventListener = listingEventListener;
        }
    }
}
