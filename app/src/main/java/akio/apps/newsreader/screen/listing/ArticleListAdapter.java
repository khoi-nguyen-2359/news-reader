package akio.apps.newsreader.screen.listing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import akio.apps.newsreader.databinding.ItemListingArticleBinding;
import akio.apps.newsreader.model.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    private View.OnClickListener onArticleItemClick;

    public ArticleListAdapter(View.OnClickListener onArticleItemClick) {
        this.onArticleItemClick = onArticleItemClick;
    }

    private AsyncListDiffer<Article> listDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(ItemListingArticleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false).getRoot());
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

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private final ItemListingArticleBinding viewBinding;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            viewBinding = ItemListingArticleBinding.bind(itemView);
        }

        void bind(Article article) {
            viewBinding.listingArticleTitleText.setText(article.getTitle());
            viewBinding.listingArticleDescriptionText.setText(HtmlCompat.fromHtml(article.getDescription(), HtmlCompat.FROM_HTML_MODE_COMPACT));
            viewBinding.listingArticlePubDateText.setText(article.getPubDateTime());
        }
    }

    static final DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getLink().equals(newItem.getLink());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPubDateTime().equals(newItem.getPubDateTime());
        }
    };
}
