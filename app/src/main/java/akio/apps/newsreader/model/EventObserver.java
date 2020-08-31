package akio.apps.newsreader.model;

import androidx.lifecycle.Observer;

public class EventObserver<T> implements Observer<Event<T>> {

    private Observer<T> actualObserver;

    public EventObserver(Observer<T> actualObserver) {
        this.actualObserver = actualObserver;
    }

    @Override
    public void onChanged(Event<T> tEvent) {
        T content = tEvent.getContentIfNotHandled();
        if (content != null) {
            actualObserver.onChanged(content);
        }
    }
}
