package hr.lordsofsmell.parfume.domain.interactor;

import android.support.annotation.NonNull;

import hr.lordsofsmell.parfume.feature.core.ICore;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class CompletableUseCase<Params> implements ICore.CompletableInteractor<Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private CompositeDisposable compositeDisposable;

    public CompletableUseCase(@NonNull ThreadExecutor threadExecutor,
                              @NonNull PostExecutionThread postExecutionThread) {
        this.compositeDisposable = new CompositeDisposable();
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Completable createCompletable(Params params);

    @Override
    public void execute(Params params, @NonNull final CompletableObserver observer) {
        createCompletable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                    }
                });
    }

    @Override
    public void cancel() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }
}
