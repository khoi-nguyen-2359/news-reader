package akio.apps.newsreader.feature.listing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import akio.apps.newsreader.model.Article;
import akio.apps.newsreader.model.Event;

public abstract class ListingViewModel extends ViewModel {
    abstract LiveData<List<Article>> getArticleList();
    abstract LiveData<Event<Throwable>> getError();
    abstract LiveData<Boolean> isInProgress();

    abstract void reloadFeed();
}
