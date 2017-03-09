package hr.lordsofsmell.parfume;

import android.app.Application;

import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.components.DaggerAppComponent;

/**
 * Application class
 *
 * @author Karlo Vrbic
 */
public class AndroidApplication extends Application {

    /** Application component for Dagger2 dependency injection */
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        createAppComponent();
    }

    /**
     * Returns the application component.
     *
     * @return the application component
     */
    public AppComponent getApplicationComponent() {
        return appComponent;
    }

    /**
     * Creates the application component.
     */
    private void createAppComponent() {
        appComponent = DaggerAppComponent.create();
    }
}
