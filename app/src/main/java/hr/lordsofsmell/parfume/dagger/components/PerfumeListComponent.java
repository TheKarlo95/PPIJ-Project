package hr.lordsofsmell.parfume.dagger.components;

import dagger.Subcomponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeListModule;
import hr.lordsofsmell.parfume.feature.perfumelist.view.PerfumeListActivity;

@Subcomponent(modules = {PerfumeListModule.class})
public interface PerfumeListComponent {
    void inject(PerfumeListActivity activity);
}
