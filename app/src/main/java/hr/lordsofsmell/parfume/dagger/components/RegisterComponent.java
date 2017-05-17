package hr.lordsofsmell.parfume.dagger.components;

import dagger.Subcomponent;
import hr.lordsofsmell.parfume.dagger.modules.RegisterModule;
import hr.lordsofsmell.parfume.feature.register.view.RegisterActivity;

@Subcomponent(modules = {RegisterModule.class})
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
