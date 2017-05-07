package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ThreadModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new ThreadExecutor(Schedulers.newThread());
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new PostExecutionThread(AndroidSchedulers.mainThread());
    }
}
