package hr.lordsofsmell.parfume.threads;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
public class PostExecutionThread {

    private Scheduler scheduler;

    public PostExecutionThread(@NonNull Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
