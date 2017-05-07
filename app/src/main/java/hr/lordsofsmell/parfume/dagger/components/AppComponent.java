package hr.lordsofsmell.parfume.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeListModule;
import hr.lordsofsmell.parfume.dagger.modules.RegisterModule;
import hr.lordsofsmell.parfume.dagger.modules.core.ApiModule;
import hr.lordsofsmell.parfume.dagger.modules.core.ClientModule;
import hr.lordsofsmell.parfume.dagger.modules.core.ConverterModule;
import hr.lordsofsmell.parfume.dagger.modules.core.HostModule;
import hr.lordsofsmell.parfume.dagger.modules.core.LogModule;
import hr.lordsofsmell.parfume.dagger.modules.core.RepositoryModule;
import hr.lordsofsmell.parfume.dagger.modules.core.ThreadModule;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
@Component(modules = {
        ApiModule.class,
        ClientModule.class,
        ConverterModule.class,
        HostModule.class,
        LogModule.class,
        RepositoryModule.class,
        ThreadModule.class
})
@Singleton
public interface AppComponent {
    PerfumeListComponent plus(PerfumeListModule module);

    RegisterComponent plus(RegisterModule module);
}
