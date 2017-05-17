package hr.lordsofsmell.parfume.feature.core;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.CompletableObserver;
import io.reactivex.observers.DisposableObserver;

public interface ICore {

    interface View {
        void showLoading();

        void hideLoading();

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

        void cancel();
    }

    interface CompletableInteractor<Params> {
        void execute(Params params, @NonNull CompletableObserver observer);

        void cancel();
    }
}
