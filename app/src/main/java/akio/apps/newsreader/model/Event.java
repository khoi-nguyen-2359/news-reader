package akio.apps.newsreader.model;

import androidx.annotation.Nullable;

public class Event<T> {

    private @Nullable T content;

    private Boolean hasBeenHandled = false;

    /**
     * Returns the content and prevents its use again.
     */
    public @Nullable T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    @Nullable T peekContent() {
        return content;
    }
}