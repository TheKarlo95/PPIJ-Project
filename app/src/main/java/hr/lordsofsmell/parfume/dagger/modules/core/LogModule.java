package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Dagger 2 module that provides HTTP client logging related stuff.
 *
 * @author Karlo Vrbic
 */
@Module
class LogModule {

    /**
     * Provides {@link HttpLoggingInterceptor.Level} that indicates what will and what won't be logged by logger.
     *
     * @return {@link HttpLoggingInterceptor.Level} that indicates what will and what won't be logged by logger
     */
    @Provides
    @Singleton
    HttpLoggingInterceptor.Level provideLogLevel() {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY;
        } else {
            return HttpLoggingInterceptor.Level.NONE;
        }
    }

    /**
     * Provides {@link HttpLoggingInterceptor} that logs HTTP client activity.
     *
     * @param level level of logging
     * @return {@link HttpLoggingInterceptor} that logs HTTP client activity.
     */
    @Provides
    @Singleton
    HttpLoggingInterceptor provideLog(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level);
        return interceptor;
    }
}
