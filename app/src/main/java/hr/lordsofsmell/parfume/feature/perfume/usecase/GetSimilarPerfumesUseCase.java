package hr.lordsofsmell.parfume.feature.perfume.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.params.PerfumeParams;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfume.IPerfume;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;
import retrofit2.Response;

public class GetSimilarPerfumesUseCase extends UseCase<PerfumeParams, Response<List<PerfumeItem>>>
        implements IPerfume.GetSimilarPerfumesUseCase {

    private IRepository repository;

    @Inject
    GetSimilarPerfumesUseCase(@NonNull ThreadExecutor threadExecutor,
                              @NonNull PostExecutionThread postExecutionThread,
                              @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Response<List<PerfumeItem>>> createObservable(PerfumeParams params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameters can't be null"));
        } else {
            return repository.getSimilarParfumes(params.token(), params.perfumeId());
        }
    }
}
