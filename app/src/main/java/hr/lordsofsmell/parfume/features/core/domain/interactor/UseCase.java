package hr.lordsofsmell.parfume.features.core.domain.interactor;

import android.support.annotation.NonNull;

import hr.lordsofsmell.parfume.features.core.ICore;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<T> implements ICore.Interactor<T> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private CompositeDisposable compositeDisposable;

    public UseCase(@NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread) {
        this.compositeDisposable = new CompositeDisposable();
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    abstract Observable<T> createObservable();

    @Override
    public void execute(DisposableObserver<T> observer) {
        Disposable disposable = this.createObservable()
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer);

        addDisposable(disposable);
    }

    @Override
    public Observable<T> execute() {
        return createObservable();
    }

    @Override
    public void cancel() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }

    private void addDisposable(@NonNull Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
