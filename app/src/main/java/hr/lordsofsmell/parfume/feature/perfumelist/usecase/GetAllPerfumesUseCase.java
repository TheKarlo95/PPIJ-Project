package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.params.GetAllPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class GetAllPerfumesUseCase extends UseCase<GetAllPerfumesParams, List<PerfumeItem>>
        implements IPerfumeList.GetAllPerfumesUseCase {

    private IRepository repository;

    @Inject
    GetAllPerfumesUseCase(@NonNull ThreadExecutor threadExecutor,
                          @NonNull PostExecutionThread postExecutionThread,
                          @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<PerfumeItem>> createObservable(GetAllPerfumesParams params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameters can't be null"));
        } else if (params.page() <= 0) {
            return Observable.error(new IllegalArgumentException(
                    "Parameter page can't be less than or equals to 0"));
        } else {
            return repository.getAllParfumes(params.token(),
                    params.page(),
                    params.company(),
                    params.model(),
                    params.year());
        }
    }
}
