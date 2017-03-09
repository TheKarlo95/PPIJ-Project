package hr.lordsofsmell.parfume.dagger.modules.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Dagger 2 module that provides thread related stuff.
 *
 * @author Karlo Vrbic
 */
@Module
class ThreadModule {

    /**
     * Provides singleton {@link ThreadExecutor} for executing tasks on new thread.
     *
     * @return {@link ThreadExecutor} for executing tasks
     */
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new ThreadExecutor(Schedulers.newThread());
    }

    /**
     * Provides singleton {@link PostExecutionThread} for executing post execution tasks on main thread.
     *
     * @return {@link PostExecutionThread} for executing post execution tasks
     */
    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new PostExecutionThread(AndroidSchedulers.mainThread());
    }
}
