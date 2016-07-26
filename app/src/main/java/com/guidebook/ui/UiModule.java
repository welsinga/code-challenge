package com.guidebook.ui;

import android.content.Context;

import com.guidebook.misc.PicassoBigCache;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for Ui related content.
 *
 * @author Wiebe
 */
@Module
public class UiModule {

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        return PicassoBigCache.INSTANCE.getPicassoBigCache(context);
    }


}

