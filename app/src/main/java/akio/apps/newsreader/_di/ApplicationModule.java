package akio.apps.newsreader._di;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Named;

import akio.apps.newsreader.NewsReaderApplication;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = {ApplicationModule.Bindings.class})
public class ApplicationModule {

    @Module
    interface Bindings {
        @Binds
        ViewModelProvider.Factory viewModelFactory(ViewModelFactory factory);

        @Binds
        Context appContext(NewsReaderApplication application);
    }

    @Provides
    @Named(NAMED_IO_EXECUTOR)
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(4, new ThreadFactory() {
            private static final String THREAD_NAME_STEM = "newsreader_disk_io_%d";

            private final AtomicInteger mThreadId = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName(String.format(THREAD_NAME_STEM, mThreadId.getAndIncrement()));
                return t;
            }
        });
    }

    public static final String NAMED_IO_EXECUTOR = "NAMED_IO_EXECUTOR";
}
