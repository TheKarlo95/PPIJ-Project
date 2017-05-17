package hr.lordsofsmell.parfume.dagger.components;

import dagger.Subcomponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeModule;
import hr.lordsofsmell.parfume.feature.perfume.view.PerfumeActivity;

@Subcomponent(modules = {PerfumeModule.class})
public interface PerfumeComponent {
    void inject(PerfumeActivity activity);
}
