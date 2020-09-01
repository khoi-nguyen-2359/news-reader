package akio.apps.newsreader.feature;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import akio.apps.newsreader.R;
import akio.apps.newsreader.databinding.ActivityHomeBinding;
import akio.apps.newsreader.feature.browser.BrowserFragment;
import akio.apps.newsreader.feature.listing.ListingEventListener;
import akio.apps.newsreader.feature.listing.ListingFragment;
import akio.apps.newsreader.feature.preferences.ApplicationPreferencesFragment;
import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding viewBinding;

    private ListingEventListener listingEventListener = article -> {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_content_fragment_container, BrowserFragment.createInstance(article.getLink()))
                .addToBackStack(null)
                .commit();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        viewBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_content_fragment_container, ListingFragment.createInstance())
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_side_content_fragment_container, ApplicationPreferencesFragment.createInstance())
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
