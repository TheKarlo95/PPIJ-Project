package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.CompletableUseCase;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Completable;

public class LogoutUseCase extends CompletableUseCase<String>
        implements IPerfumeList.LogoutUseCase {

    private IRepository repository;

    @Inject
    LogoutUseCase(@NonNull ThreadExecutor threadExecutor,
                  @NonNull PostExecutionThread postExecutionThread,
                  @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable createCompletable(String token) {
        if (token == null) {
            return Completable.error(new NullPointerException("Parameter token can't be null"));
        } else {
            return repository.logout(token);
        }
    }


}
