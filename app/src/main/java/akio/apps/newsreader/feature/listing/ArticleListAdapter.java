package akio.apps.newsreader.feature.listing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import akio.apps.newsreader.databinding.ItemListingArticleBinding;
import akio.apps.newsreader.model.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    @Nullable
    private ListingEventListener listingEventListener;

    private AsyncListDiffer<Article> listDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);

    public ArticleListAdapter(@Nullable ListingEventListener listingEventListener) {
        this.listingEventListener = listingEventListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListingArticleBinding itemViewBinding = ItemListingArticleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ArticleViewHolder(
                itemViewBinding,
                listingEventListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(listDiffer.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return listDiffer.getCurrentList().size();
    }

    public void submitList(List<Article> articleList) {
        listDiffer.submitList(articleList);
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ItemListingArticleBinding viewBinding;

        @Nullable
        private ListingEventListener listingEventListener;

        private Article boundArticle;

        public ArticleViewHolder(@NonNull ItemListingArticleBinding itemViewBinding, @Nullable ListingEventListener listingEventListener) {
            super(itemViewBinding.getRoot());

            viewBinding = itemViewBinding;
            this.listingEventListener = listingEventListener;
            itemView.setOnClickListener(this);
        }

        void bind(Article article) {
            boundArticle = article;
            viewBinding.listingArticleTitleText.setText(article.getTitle());
            viewBinding.listingArticleDescriptionText.setText(HtmlCompat.fromHtml(article.getDescription(), HtmlCompat.FROM_HTML_MODE_COMPACT));
            viewBinding.listingArticlePubDateText.setText(article.getPubDateTime());
        }

        @Override
        public void onClick(View view) {
            if (listingEventListener != null)
                listingEventListener.onClickArticleItem(boundArticle);
        }
    }

    static final DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getGuid().equals(newItem.getGuid()) && oldItem.getLink().equals(newItem.getLink());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPubDateTime().equals(newItem.getPubDateTime());
        }
    };
}
