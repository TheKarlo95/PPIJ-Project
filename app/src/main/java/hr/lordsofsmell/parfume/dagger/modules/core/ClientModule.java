package hr.lordsofsmell.parfume.dagger.modules.core;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
