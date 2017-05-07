package hr.lordsofsmell.parfume.domain.interactor;

import android.support.annotation.NonNull;

import hr.lordsofsmell.parfume.feature.core.ICore;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<Params, T> implements ICore.Interactor<Params, T> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private CompositeDisposable compositeDisposable;

    public UseCase(@NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread) {
        this.compositeDisposable = new CompositeDisposable();
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<T> createObservable(Params params);

    @Override
    public void execute(Params params, DisposableObserver<T> observer) {
        Observable<T> observable = createObservable(params);
        Disposable disposable = createObservable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer);

        addDisposable(disposable);
    }

    @Override
    public Observable<T> execute(Params params) {
        return createObservable(params);
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
