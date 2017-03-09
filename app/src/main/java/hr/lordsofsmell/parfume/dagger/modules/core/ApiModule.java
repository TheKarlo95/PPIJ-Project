package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.features.core.data.network.ApiService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Dagger 2 module that provides API related stuff.
 *
 * @author Karlo Vrbic
 */
@Module
class ApiModule {

    /**
     * Provides singleton {@link ApiService} object that handles all API calls.
     *
     * @param client      HTTP client used for making API requests and getting responses
     * @param callFactory
     * @param gson
     * @param scalar
     * @param endpoint
     * @return
     */
    @Provides
    @Singleton
    ApiService provideApiService(OkHttpClient client,
                                 CallAdapter.Factory callFactory,
                                 @Named("gson") Converter.Factory gson,
                                 @Named("scalar") Converter.Factory scalar,
                                 HttpUrl endpoint) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addCallAdapterFactory(callFactory)
                .addConverterFactory(scalar)
                .addConverterFactory(gson)
                .build();

        return retrofit.create(ApiService.class);
    }
}