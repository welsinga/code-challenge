package com.guidebook;

import android.content.Context;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * GuidebookApplication "delegate".
 */
public class GuidebookApplication extends android.app.Application {

    private GuidebookComponent _componentGraph;

    /**
     * Guidebook start-up.
     * <p>
     * Perform the following tasks:
     * - Setup Dagger 2
     */
    public void onCreate() {
        super.onCreate();

        // Start creation of singletons so inject() won't hang UI
        Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                // Build the Dagger 2 component and execute all injections
                _componentGraph = GuidebookComponent.Initializer.init(GuidebookApplication.this);
                component().inject(GuidebookApplication.this);
                return null;
            }
        }).subscribeOn(Schedulers.computation()).subscribe();

    }

    /**
     * Static method for getting a reference to the GuidebookApplication instance itself.
     *
     * @param context The activity context.
     * @return The GuidebookApplication reference.
     */
    public static GuidebookApplication get(Context context) {
        return (GuidebookApplication)context.getApplicationContext();
    }

    /**
     * Returns the dependency component.
     *
     * @return The Dagger dependency management component.
     */
    public GuidebookComponent component() {
        return _componentGraph;
    }
}