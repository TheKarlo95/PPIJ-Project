package hr.lordsofsmell.parfume.features.core;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Karlo Vrbic on 08.03.17.
 */
public abstract class ViewObserver<T> extends DisposableObserver<T> {

    @NonNull private ICore.View view;
    @StringRes private int errorId;

    public ViewObserver(@NonNull ICore.View view, @StringRes int errorId) {
        this.view = view;
        this.errorId = errorId;
    }

    @Override
    public void onError(Throwable e) {
        view.hideLoading();
        view.showError(errorId);
    }

    @Override
    public void onComplete() {
        view.hideLoading();
    }

    protected ICore.View getView() {
        return view;
    }
}
