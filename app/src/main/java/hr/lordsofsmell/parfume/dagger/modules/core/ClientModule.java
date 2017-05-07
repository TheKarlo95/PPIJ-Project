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
public class ClientModule {

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
