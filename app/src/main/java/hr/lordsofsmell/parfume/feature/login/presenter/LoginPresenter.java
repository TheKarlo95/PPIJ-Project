package hr.lordsofsmell.parfume.feature.login.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.ICore;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.login.ILogin;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by tea03 on 5/7/2017.
 */

public class LoginPresenter extends Presenter implements ILogin.Presenter {
    private static final String FIREBASE_TAG = "Firebase";

    private ILogin.View view;
    private ILogin.LoginUseCase loginUseCase;

    public LoginPresenter(@NonNull ICore.View view, @NonNull ILogin.LoginUseCase loginUseCase) {
        super(view);
        this.loginUseCase = loginUseCase;
    }

    @Override
    public void login(@NonNull String username, @NonNull String password) {
        LoginRequest loginRequest = LoginRequest.create(username, password);
        final ILogin.View view=(ILogin.View)getView();
        loginUseCase.execute(loginRequest, new Observer<User>() {
            @Override
            public void onNext(User value) {
                super.onNext(value);
                view.loginSuccesful(value);
            }
        });
    }
}
