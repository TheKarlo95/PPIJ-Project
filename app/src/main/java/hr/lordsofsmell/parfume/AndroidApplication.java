package hr.lordsofsmell.parfume;

import android.app.Application;

import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.components.DaggerAppComponent;

public class AndroidApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
