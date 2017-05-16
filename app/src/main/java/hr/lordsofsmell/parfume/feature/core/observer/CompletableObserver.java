package hr.lordsofsmell.parfume.feature.core.observer;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import hr.lordsofsmell.parfume.feature.core.ICore;

public class CompletableObserver extends CompletableLoggingObserver {

    @StringRes private int errorId;

    public CompletableObserver(@NonNull ICore.View view,
                               @NonNull String tag,
                               @StringRes int errorId) {
        super(view, tag);
        this.errorId = errorId;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        getView().showError(errorId);
    }
}
