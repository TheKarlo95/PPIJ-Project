package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.BuildConfig;
import okhttp3.HttpUrl;

/**
 * Dagger 2 module that provides host related stuff.
 *
 * @author Karlo Vrbic
 */
@Module
class HostModule {

    /** Network timeout in seconds for debug version of build */
    private static final int DEBUG_NETWORK_TIMEOUT_SECONDS = 60;
    /** Network timeout in seconds for run version of build */
    private static final int NETWORK_TIMEOUT_SECONDS = 5;
    // TODO add real API URL
    /** API URL endpoint */
    private static final String API_URL = "https://example-api-url.com";

    /**
     * Provides {@link HttpUrl} which represents APIs endpoint.
     *
     * @return {@link HttpUrl} which represents APIs endpoint
     */
    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(API_URL);
    }

    /**
     * Provides number of seconds for network timeout. If it is debug version it will provide
     * {@value DEBUG_NETWORK_TIMEOUT_SECONDS} and if it is not debug version it will provide
     * {@value NETWORK_TIMEOUT_SECONDS}.
     *
     * @return number of seconds for network timeout
     */
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
