package akio.apps.newsreader.feature.listing;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LazyImageGetter implements Html.ImageGetter {
    private static Html.ImageGetter instance;

    public static Html.ImageGetter getInstance() {
        if (instance == null) {
            instance = new LazyImageGetter();
        }
        return instance;
    }

    private LazyImageGetter() { }

    @Override
    public Drawable getDrawable(String s) {
        return new EmptyDrawable();
    }

    public static class EmptyDrawable extends Drawable {
        @Override
        public void draw(@NonNull Canvas canvas) {

        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }
    }
}
