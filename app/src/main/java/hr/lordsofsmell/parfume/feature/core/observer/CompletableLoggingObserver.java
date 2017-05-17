package hr.lordsofsmell.parfume.feature.core.observer;

import android.support.annotation.NonNull;
import android.util.Log;

import hr.lordsofsmell.parfume.feature.core.ICore;
import io.reactivex.disposables.Disposable;

class CompletableLoggingObserver implements io.reactivex.CompletableObserver {

    @NonNull private ICore.View view;
    @NonNull private String tag;

    CompletableLoggingObserver(@NonNull ICore.View view, @NonNull String tag) {
        this.view = view;
        this.tag = tag;
    }

    @Override
    public void onSubscribe(Disposable d) {

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
}
