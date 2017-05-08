package hr.lordsofsmell.parfume;

import android.app.Application;
import android.content.Context;

import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.components.DaggerAppComponent;

public class AndroidApplication extends Application {

    private static Context applicationContext;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();
        appComponent = DaggerAppComponent.create();
    }

    public static Context getContext() {
        return applicationContext;
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
