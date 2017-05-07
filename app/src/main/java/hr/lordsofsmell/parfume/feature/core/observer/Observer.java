package hr.lordsofsmell.parfume.feature.core.observer;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import hr.lordsofsmell.parfume.feature.core.ICore;

public abstract class Observer<T> extends LoggingObserver<T> {

    @StringRes private int errorId;

    public Observer(@NonNull ICore.View view, @NonNull String tag, @StringRes int errorId) {
        super(view, tag);
        this.errorId = errorId;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        getView().showError(errorId);
    }
}
