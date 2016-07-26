package com.guidebook.service;

import com.guidebook.api.ApiClient;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for service related content.
 *
 * @author Wiebe
 */
@Module
public class ServiceModule {

    /**
     * Provide Guide service
     *
     * @return GuideService
     */
    @Provides
    @Singleton
    public GuideService provideGuideService(ApiClient apiClient, EventBus bus) {
        return new GuideService(apiClient, bus);
    }

}
