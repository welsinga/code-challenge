package com.guidebook.data;

import android.content.Context;

import com.guidebook.Constants;
import com.guidebook.R;
import com.guidebook.api.ApiClient;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module for data related content.
 *
 * @author Wiebe
 */
@Module
public class DataModule {

    /**
     * Provide HTTP client
     *
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Constants.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return client;
    }

    /**
     * Provide Retrofit
     *
     * @return Retrofit
     */
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient httpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(httpClient)
                .build();
        return retrofit;
    }

    /**
     * Provide API client
     *
     * @return ApiClient
     */
    @Provides
    @Singleton
    public ApiClient provideApiClient(Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }

    /**
     * Provide event bus
     */
    @Provides
    @Singleton
    public EventBus provideBus() {
        if (Constants.DEBUG) {
            return new EventBus();
        } else {
            return EventBus.builder().logNoSubscriberMessages(false).sendNoSubscriberEvent(false).build();
        }
    }


}
