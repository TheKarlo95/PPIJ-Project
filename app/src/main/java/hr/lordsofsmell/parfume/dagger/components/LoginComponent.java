package hr.lordsofsmell.parfume.dagger.components;

import dagger.Subcomponent;
import hr.lordsofsmell.parfume.dagger.modules.LoginModule;
import hr.lordsofsmell.parfume.feature.login.view.LoginActivity;

@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
