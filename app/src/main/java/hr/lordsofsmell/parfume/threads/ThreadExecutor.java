package hr.lordsofsmell.parfume.threads;

import io.reactivex.Scheduler;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
public class ThreadExecutor {

    private Scheduler scheduler;

    public ThreadExecutor(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
