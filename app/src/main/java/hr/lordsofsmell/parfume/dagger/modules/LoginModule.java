package hr.lordsofsmell.parfume.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.feature.login.ILogin;
import hr.lordsofsmell.parfume.feature.login.presenter.LoginPresenter;
import hr.lordsofsmell.parfume.feature.login.usecase.LoginUseCase;

@Module
public class LoginModule {

    private final ILogin.View view;

    public LoginModule( ILogin.View view, Context applicationContext) {
        this.view = view;
    }

    @Provides
    public  ILogin.View provideView() {
        return view;
    }

    @Provides
    public  ILogin.Presenter providePresenter(LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    public  ILogin.LoginUseCase provideLoginUseCase(LoginUseCase useCase) {
        return useCase;
    }
}
