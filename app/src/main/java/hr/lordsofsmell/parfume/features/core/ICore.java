package hr.lordsofsmell.parfume.features.core;

import android.support.annotation.StringRes;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public interface ICore {

    interface View {
        void showLoading();

        void hideLoading();

        void showMessage(@StringRes int stringId);

        void showError(@StringRes int stringId);
    }

    interface Presenter<T extends View> {
        void onBind();

        void onRefresh();

        void onStop();
    }

    interface Interactor<T> {
        void execute(DisposableObserver<T> observer);

        Observable<T> execute();

        void cancel();
    }
}
