package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class LogModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor.Level provideLogLevel() {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY;
        } else {
            return HttpLoggingInterceptor.Level.NONE;
        }
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLog(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level);
        return interceptor;
    }
}
