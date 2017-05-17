package hr.lordsofsmell.parfume.threads;

import io.reactivex.Scheduler;

public class ThreadExecutor {

    private Scheduler scheduler;

    public ThreadExecutor(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
