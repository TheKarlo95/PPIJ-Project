package hr.lordsofsmell.parfume.dagger.modules.core;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
@Module
class ClientModule {

    /**
     * Provdes {@link OkHttpClient} which is used for sending HTTP requests and receiving HTTP responses.
     *
     * @param logger               logs HTTP requests and responses
     * @param networkTimoutSeconds number of seconds before connection closes
     * @return {@link OkHttpClient} which is used for sending HTTP requests and receiving HTTP responses
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logger, Integer networkTimoutSeconds) {
        return new OkHttpClient.Builder()
                .readTimeout(networkTimoutSeconds, TimeUnit.SECONDS)
                .connectTimeout(networkTimoutSeconds, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build();
    }
}
