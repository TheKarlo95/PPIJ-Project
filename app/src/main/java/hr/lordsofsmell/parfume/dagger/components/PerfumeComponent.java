package hr.lordsofsmell.parfume.dagger.components;

import dagger.Subcomponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeModule;
import hr.lordsofsmell.parfume.dagger.modules.RegisterModule;
import hr.lordsofsmell.parfume.feature.perfumeProfile.view.PerfumeActivity;
import hr.lordsofsmell.parfume.feature.register.view.RegisterActivity;

@Subcomponent(modules = {PerfumeModule.class})
public interface PerfumeComponent {
    void inject(PerfumeActivity activity);
}
