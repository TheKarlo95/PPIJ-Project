package hr.lordsofsmell.parfume.feature.core;

import android.support.annotation.StringRes;

import io.reactivex.Observable;
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

        void onError(@StringRes int stringId);
    }

    interface Interactor<Params, T> {
        void execute(Params params, DisposableObserver<T> observer);

        Observable<T> execute(Params params);

        void cancel();
    }
}
