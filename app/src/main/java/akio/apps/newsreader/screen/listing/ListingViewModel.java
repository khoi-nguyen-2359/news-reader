package akio.apps.newsreader.screen.listing;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import akio.apps.newsreader.data.article.ArticleRepository;

public class ListingViewModel extends ViewModel {

    private final ArticleRepository articleRepository;

    @Inject
    public ListingViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

}
