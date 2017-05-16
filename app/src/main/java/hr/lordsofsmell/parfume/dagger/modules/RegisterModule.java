package hr.lordsofsmell.parfume.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.feature.register.IRegister;
import hr.lordsofsmell.parfume.feature.register.presenter.RegisterPresenter;
import hr.lordsofsmell.parfume.feature.register.usecase.RegisterUseCase;

@Module
public class RegisterModule {

    private final IRegister.View view;
    private final Context applicationContext;

    public RegisterModule(IRegister.View view, Context applicationContext) {
        this.view = view;
        this.applicationContext = applicationContext;
    }

    @Provides
    public IRegister.View provideView() {
        return view;
    }

    @Provides
    public Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides
    public IRegister.Presenter providePresenter(RegisterPresenter presenter) {
        return presenter;
    }

    @Provides
    public IRegister.RegisterUseCase provideGetAllPerfumesUseCase(RegisterUseCase useCase) {
        return useCase;
    }


}
