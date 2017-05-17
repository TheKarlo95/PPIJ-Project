package hr.lordsofsmell.parfume.feature.perfumeProfile.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.params.PerfumeParams;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class GetSimilarPerfumesUseCase extends UseCase<PerfumeParams, List<PerfumeItem>>
        implements IPerfumeProfile.GetSimilarPerfumesUseCase {

    private IRepository repository;

    @Inject
    GetSimilarPerfumesUseCase(@NonNull ThreadExecutor threadExecutor,
                              @NonNull PostExecutionThread postExecutionThread,
                              @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<PerfumeItem>> createObservable(PerfumeParams params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameters can't be null"));
        } else {
            return repository.getSimilarParfumes(params.token(), params.perfumeId());
        }
    }
}
