package hr.lordsofsmell.parfume.feature.login.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.ICore;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.login.ILogin;
import io.reactivex.observers.DisposableObserver;

public class LoginPresenter extends Presenter implements ILogin.Presenter {

    private static final String TAG = "LoginPresenter";

    private ILogin.View view;
    private ILogin.LoginUseCase loginUseCase;

    @Inject
    LoginPresenter(@NonNull ILogin.View view, @NonNull ILogin.LoginUseCase loginUseCase) {
        super(view);
        this.loginUseCase = loginUseCase;
    }

    @Override
    public void login(@NonNull String username, @NonNull String password) {
        LoginRequest loginRequest = LoginRequest.create(username, password);
        final ILogin.View view=(ILogin.View)getView();
        loginUseCase.execute(loginRequest, new Observer<User>(view, TAG, R.string.login_error) {
            @Override
            public void onNext(User value) {
                super.onNext(value);
                view.loginSuccesful(value);
            }
        });
    }
}
