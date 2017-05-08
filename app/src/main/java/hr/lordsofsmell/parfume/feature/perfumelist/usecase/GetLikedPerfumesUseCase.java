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

public class GetLikedPerfumesUseCase extends UseCase<Long, List<PerfumeItem>>
        implements IPerfumeList.GetLikedPerfumesUseCase {

    private IRepository repository;

    @Inject
    GetLikedPerfumesUseCase(@NonNull ThreadExecutor threadExecutor,
                            @NonNull PostExecutionThread postExecutionThread,
                            @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<PerfumeItem>> createObservable(Long userId) {
        if (userId == null) {
            return Observable.error(new NullPointerException("Parameter userId can't be null"));
        } else if (userId <= 0) {
            return Observable.error(new IllegalArgumentException("Parameter userId can't be less than or equals to 0"));
        } else {
            return repository.getLikedParfumes(userId);
        }
    }
}
