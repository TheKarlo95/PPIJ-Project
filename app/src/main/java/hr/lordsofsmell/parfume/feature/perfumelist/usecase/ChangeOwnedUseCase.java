package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.CompletableUseCase;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Completable;

public class ChangeOwnedUseCase extends CompletableUseCase<OwnedRequestParams>
        implements IPerfumeList.ChangeOwnedUseCase {

    private IRepository repository;

    @Inject
    ChangeOwnedUseCase(@NonNull ThreadExecutor threadExecutor,
                       @NonNull PostExecutionThread postExecutionThread,
                       @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable createCompletable(OwnedRequestParams params) {
        if (params == null) {
            return Completable.error(new NullPointerException("Parameter params can't be null"));
        } else if (params.request().parfumeId() <= 0) {
            return Completable.error(new IllegalArgumentException(
                    "Parameter parfumeId can't be less than or equals to 0"));
        } else {
            return repository.changeOwned(params.token(), params.request());
        }
    }
}
