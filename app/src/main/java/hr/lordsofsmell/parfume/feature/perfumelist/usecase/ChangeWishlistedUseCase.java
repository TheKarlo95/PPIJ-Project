package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class ChangeWishlistedUseCase extends UseCase<WishlistedRequestParams, Void>
        implements IPerfumeList.ChangeWishlistedUseCase {

    private IRepository repository;

    @Inject
    ChangeWishlistedUseCase(@NonNull ThreadExecutor threadExecutor,
                            @NonNull PostExecutionThread postExecutionThread,
                            @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Void> createObservable(WishlistedRequestParams params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameter params can't be null"));
        } else if (params.request().parfumeId() <= 0) {
            return Observable.error(new IllegalArgumentException("Parameter parfumeId can't be less than or equals to 0"));
        } else {
            return repository.changeWishlisted(params.userId(), params.request());
        }
    }
}
