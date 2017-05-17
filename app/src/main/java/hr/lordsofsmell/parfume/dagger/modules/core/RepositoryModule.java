package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.domain.repository.Repository;
import hr.lordsofsmell.parfume.domain.repository.mock.MockRepository;
import hr.lordsofsmell.parfume.domain.repository.network.ApiService;
import hr.lordsofsmell.parfume.domain.repository.network.NetworkDataSource;

@Module
public class RepositoryModule {

    private static final boolean USE_MOCK_REPOSITORY = false;

    @Provides
    @Singleton
    IRepository provideRepository(@Named("real") IRepository realRepository,
                                  @Named("mock") IRepository mockRepository) {
        if (USE_MOCK_REPOSITORY) {
            return mockRepository;
        } else {
            return realRepository;
        }
    }

    @Provides
    @Singleton
    @Named("mock")
    IRepository provideMockRepository() {
        return new MockRepository();
    }

    @Provides
    @Singleton
    @Named("real")
    IRepository provideRealRepository(NetworkDataSource networkDataSource) {
        return new Repository(networkDataSource);
    }

    @Provides
    @Singleton
    NetworkDataSource provideNetworkDataSource(ApiService apiService) {
        return new NetworkDataSource(apiService);
    }
}
