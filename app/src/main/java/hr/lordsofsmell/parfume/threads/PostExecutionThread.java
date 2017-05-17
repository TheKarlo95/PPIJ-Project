package hr.lordsofsmell.parfume.threads;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public class PostExecutionThread {

    private Scheduler scheduler;

    public PostExecutionThread(@NonNull Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
