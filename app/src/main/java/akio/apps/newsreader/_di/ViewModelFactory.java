package akio.apps.newsreader._di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Iterator<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> entrySet = creators.entrySet().iterator();
        Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> found = null;
        while (entrySet.hasNext()) {
            Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry = entrySet.next();
            if (modelClass.isAssignableFrom(entry.getKey())) {
                found = entry;
                break;
            }
        }

        Provider<ViewModel> creator = null;
        if (found != null) {
            creator = found.getValue();
        }

        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass.toString());
        }

        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
