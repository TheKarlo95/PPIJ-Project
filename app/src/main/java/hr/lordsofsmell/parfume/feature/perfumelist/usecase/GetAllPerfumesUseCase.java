package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class GetAllPerfumesUseCase extends UseCase<Void, List<PerfumeItem>>
        implements IPerfumeList.GetAllPerfumesUseCase {

    private IRepository repository;

    @Inject
    public GetAllPerfumesUseCase(@NonNull ThreadExecutor threadExecutor,
                                 @NonNull PostExecutionThread postExecutionThread,
                                 @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<PerfumeItem>> createObservable(Void aVoid) {
        return repository.getAllParfumes();
    }
}
