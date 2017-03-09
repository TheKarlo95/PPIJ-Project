package hr.lordsofsmell.parfume.features.core.data.memory;

import android.support.annotation.NonNull;

/**
 * Created by Karlo Vrbic on 08.03.17.
 */
public class MemoryItem<T> {

    @NonNull private T item;
    private long secondsToInvalidate;
    private long timeWhenSet;

    public MemoryItem(@NonNull T item, long secondsToInvalidate) {
        this.item = item;
        this.secondsToInvalidate = secondsToInvalidate;
    }

    public MemoryItem(@NonNull T item) {
        this(item, 60L);
    }

    public boolean isInvalidated() {
        long timePassed = (System.currentTimeMillis() - timeWhenSet) / 1000;
        return timePassed > secondsToInvalidate;
    }

    public T getItem() {
        return getItem(false);
    }

    public T getItem(boolean ignoreInvalidation) {
        if (!ignoreInvalidation && isInvalidated()) {
            return null;
        } else {
            return item;
        }
    }

    public void setItem(@NonNull T item) {
        this.item = item;
        this.timeWhenSet = System.currentTimeMillis();
    }
}
