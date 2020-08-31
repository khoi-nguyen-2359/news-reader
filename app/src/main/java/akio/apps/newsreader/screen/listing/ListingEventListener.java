package akio.apps.newsreader.screen.listing;

import akio.apps.newsreader.model.Article;

public interface ListingEventListener {
    void onClickArticleItem(Article article);
}
