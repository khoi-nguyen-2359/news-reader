package akio.apps.newsreader.feature.listing;

import akio.apps.newsreader.model.Article;

public interface ListingEventListener {
    void onClickArticleItem(Article article);
}
