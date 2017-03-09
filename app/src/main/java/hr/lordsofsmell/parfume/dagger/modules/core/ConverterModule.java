package hr.lordsofsmell.parfume.dagger.modules.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
@Module
class ConverterModule {

    /**
     * Provides {@link RxJava2CallAdapterFactory} as a factory that makes all API calls return {@link Observable}
     * object.
     *
     * @return RxJava2 call adapter factory
     */
    @Provides
    @Singleton
    CallAdapter.Factory provideCallFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    /**
     * Provides {@link GsonConverterFactory} that converts Java objects to JSON.
     *
     * @param gson {@link Gson} converter
     * @return {@link GsonConverterFactory} that converts Java objects into JSON response
     */
    @Provides
    @Singleton
    @Named("gson")
    Converter.Factory provideGsonConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    /**
     * Provides {@link ScalarsConverterFactory} that converts strings and both primitives and their boxed types to
     * {@code text/plain} bodies.
     *
     * @return {@link ScalarsConverterFactory} that converts strings and primitives
     */
    @Provides
    @Singleton
    @Named("scalar")
    Converter.Factory provideScalarConverter() {
        return ScalarsConverterFactory.create();
    }

    /**
     * Provides {@link Gson} converter that converts objects to JSON.
     *
     * @return {@link Gson} converter that converts objects to JSON.
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }
}
