package com.guidebook;

import com.guidebook.activity.MainActivity;
import com.guidebook.ui.GuideViewHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger module for application related content.
 *
 * @author Wiebe
 */

@Singleton
@Component(modules = { AppModule.class })
public interface GuidebookComponent {

    /**
     * An initializer that creates the graph from an application.
     */
    final static class Initializer {
        public static GuidebookComponent init(GuidebookApplication app) {
            // Don't worry if you see an error here, DaggerGuidebookComponent is generated while building.
            return DaggerGuidebookComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }

        private Initializer() {
        } // No instances.
    }

    // Activities
    void inject(MainActivity activity);

    // ViewHolders
    void inject(GuideViewHolder viewHolder);

    // Others
    void inject(GuidebookApplication application);
}
