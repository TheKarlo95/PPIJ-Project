package hr.lordsofsmell.parfume.feature.perfumeProfile.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.params.PerfumeParams;
import hr.lordsofsmell.parfume.domain.model.response.Perfume;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class GetPerfumeUseCase extends UseCase<PerfumeParams, Perfume>
        implements IPerfumeProfile.GetPerfumeUseCase {

    private IRepository repository;

    @Inject
    GetPerfumeUseCase(@NonNull ThreadExecutor threadExecutor,
                      @NonNull PostExecutionThread postExecutionThread,
                      @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Perfume> createObservable(PerfumeParams params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameters can't be null"));
        } else {
            return repository.getPerfumeProfile(params.token(), params.perfumeId());
        }
    }
}
