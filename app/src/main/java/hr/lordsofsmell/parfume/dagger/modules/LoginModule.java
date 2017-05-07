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
    private final Context applicationContext;

    public LoginModule( ILogin.View view, Context applicationContext) {
        this.view = view;
        this.applicationContext = applicationContext;
    }

    @Provides
    public  ILogin.View provideView() {
        return view;
    }

    @Provides
    public Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides
    public  ILogin.Presenter providePresenter(LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    public  ILogin.LoginUseCase provideGetAllPerfumesUseCase(LoginUseCase useCase) {
        return useCase;
    }
}
