package com.guidebook;

import android.app.Application;
import android.content.Context;

import com.guidebook.data.DataModule;
import com.guidebook.service.ServiceModule;
import com.guidebook.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for application related content.
 *
 * @author Wiebe
 */
@Module(includes = { UiModule.class, DataModule.class, ServiceModule.class })
public class AppModule {

    private final Application _application;

    /**
     * Constructor
     */
    public AppModule(Application application) {
        _application = application;
    }


    /**
     * Provide ApplicationContext
     */
    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return _application;
    }

}

