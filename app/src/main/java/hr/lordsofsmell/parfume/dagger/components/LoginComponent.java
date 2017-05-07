package hr.lordsofsmell.parfume.dagger.components;

import dagger.Subcomponent;
import hr.lordsofsmell.parfume.dagger.modules.LoginModule;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeListModule;
import hr.lordsofsmell.parfume.feature.login.view.LoginActivity;
import hr.lordsofsmell.parfume.feature.perfumelist.view.PerfumeListActivity;

@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);

}
