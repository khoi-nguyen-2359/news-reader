package akio.apps.newsreader.data.article.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDao {
    @Insert(onConflict = REPLACE)
    void insertArticle(ArticleDto articleDto);

    @Transaction
    default void replaceArticles(List<ArticleDto> articleDtoList) {
        clearArticles();
        for (int i = 0; i < articleDtoList.size(); ++i) {
            insertArticle(articleDtoList.get(i));
        }
    }

    @Query("DELETE FROM article")
    void clearArticles();

    @Query("SELECT * FROM article")
    LiveData<List<ArticleDto>> getArticles();
}
