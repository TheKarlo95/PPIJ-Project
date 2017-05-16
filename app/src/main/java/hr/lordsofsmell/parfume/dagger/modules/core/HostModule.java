package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.BuildConfig;
import okhttp3.HttpUrl;

@Module
public class HostModule {

    private static final int DEBUG_NETWORK_TIMEOUT_SECONDS = 60;
    private static final int NETWORK_TIMEOUT_SECONDS = 5;
    // TODO add real API URL
    private static final String API_URL = "http://perfumeapi.azurewebsites.net/api/";

    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(API_URL);
    }

    @Provides
    @Singleton
    Integer provideNetworkTimeout() {
        if (BuildConfig.DEBUG) {
            return DEBUG_NETWORK_TIMEOUT_SECONDS;
        } else {
            return NETWORK_TIMEOUT_SECONDS;
        }
    }
}
