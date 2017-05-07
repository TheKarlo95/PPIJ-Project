package hr.lordsofsmell.parfume.feature.core.observer;

import android.support.annotation.NonNull;
import android.util.Log;

import hr.lordsofsmell.parfume.feature.core.ICore;
import io.reactivex.observers.DisposableObserver;

public class LoggingObserver<T> extends DisposableObserver<T> {

    @NonNull private ICore.View view;
    @NonNull private String tag;

    public LoggingObserver(@NonNull ICore.View view, @NonNull String tag) {
        this.view = view;
        this.tag = tag;
    }

    @Override
    public void onNext(T value) {
        Log.d(tag, "onNext: " + String.valueOf(value));
    }

    @Override
    public void onError(Throwable e) {
        view.hideLoading();
        Log.e(tag, "onError: " + String.valueOf(e));
    }

    @Override
    public void onComplete() {
        view.hideLoading();
        Log.d(tag, "onCompleted");
    }

    @NonNull
    protected ICore.View getView() {
        return view;
    }

    @NonNull
    protected String getTag() {
        return tag;
    }
}
