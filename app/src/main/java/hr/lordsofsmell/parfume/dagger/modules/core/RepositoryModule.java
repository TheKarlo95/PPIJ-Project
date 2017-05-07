package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.domain.repository.MockRepository;
import hr.lordsofsmell.parfume.domain.repository.Repository;
import hr.lordsofsmell.parfume.domain.repository.network.ApiService;
import hr.lordsofsmell.parfume.domain.repository.network.NetworkDataSource;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

@Module
public class RepositoryModule {

    private static final boolean USE_MOCK = false;

    @Provides
    @Singleton
    IRepository provideRepository(NetworkDataSource networkDataSource) {
        if (USE_MOCK) {
            return new MockRepository();
        } else {
            return new Repository(networkDataSource);
        }
    }

    @Provides
    @Singleton
    NetworkDataSource provideNetworkDataSource(ApiService apiService, PreferencesUtil preferencesUtil) {
        return new NetworkDataSource(apiService, preferencesUtil);
    }

    @Provides
    @Singleton
    PreferencesUtil providePreferencesUtil() {
        return new PreferencesUtil();
    }
}