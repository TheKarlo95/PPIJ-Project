package hr.lordsofsmell.parfume.feature.core;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

public interface ICore {

    interface View {
        void showLoading();

        void hideLoading();

        void showMessage(@StringRes int messageId);

        void showError(@StringRes int messageId);
    }

    interface Presenter {
        void onResume();

        void onPause();

        void onStop();

        void onDestroy();
    }

    interface Interactor<Params, T> {
        void execute(Params params, DisposableObserver<T> observer);

        Observable<T> execute(Params params);

        void cancel();
    }

    interface CompletableInteractor<Params> {
        void execute(Params params, @NonNull CompletableObserver observer);

        Completable execute(Params params);

        void cancel();
    }
}
