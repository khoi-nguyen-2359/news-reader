package akio.apps.newsreader.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    public Resource() {
    }

    static public class Success<T> extends Resource<T> {
        @NonNull
        private final T data;

        public Success(@NonNull T data) {
            this.data = data;
        }

        @NonNull
        public T getData() {
            return data;
        }
    }

    static public class Error<T> extends Resource<T> {
        private final Throwable error;

        @Nullable
        private final T data;

        public Error(Throwable error, @Nullable T data) {
            this.error = error;
            this.data = data;
        }

        @Nullable
        public T getData() {
            return data;
        }

        public Throwable getError() {
            return error;
        }
    }

    public static class Loading<T> extends Resource<T> {
        @Nullable
        private T data;

        public Loading(@Nullable T data) {
            this.data = data;
        }

        @Nullable
        public T getData() {
            return data;
        }
    }
}