package hr.lordsofsmell.parfume.dagger.modules.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.gson.AutoValueGsonFactory;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class ConverterModule {

    @Provides
    @Singleton
    CallAdapter.Factory provideCallFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    @Named("gson")
    Converter.Factory provideGsonConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    @Named("scalar")
    Converter.Factory provideScalarConverter() {
        return ScalarsConverterFactory.create();
    }

    @Provides
    @Singleton
    Gson provideGson(TypeAdapterFactory typeAdapterFactory) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();
    }

    @Provides
    @Singleton
    TypeAdapterFactory provideTypeAdapterFactory() {
        return AutoValueGsonFactory.create();
    }
}
