package akio.apps.newsreader.data.article.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ArticleDto.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase  {
    public abstract ArticleDao articleDao();
}
