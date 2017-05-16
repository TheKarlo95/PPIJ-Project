package hr.lordsofsmell.parfume.feature.perfumeProfile.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.params.GetAllPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.GetPerfumeProfileParams;
import hr.lordsofsmell.parfume.domain.model.response.Parfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by tea03 on 5/16/2017.
 */

public class PerfumeProfileUseCase extends UseCase<GetPerfumeProfileParams, Parfume> implements IPerfumeProfile.PerfumeProfileUseCase {

    private IRepository repository;

    public PerfumeProfileUseCase(@NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread,
                                 @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository=repository;
    }

    @Override
    protected Observable<Parfume> createObservable(GetPerfumeProfileParams params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameters can't be null"));
        } else {
            return repository.getPerfumeProfile(params.perfumeId());
        }
    }
}
