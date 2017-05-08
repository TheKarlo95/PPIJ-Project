package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class ChangeLikedUseCase extends UseCase<ChangeLikedUseCase.Params, Void>
        implements IPerfumeList.ChangeLikedUseCase {

    private IRepository repository;

    @Inject
    ChangeLikedUseCase(@NonNull ThreadExecutor threadExecutor,
                       @NonNull PostExecutionThread postExecutionThread,
                       @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Void> createObservable(Params params) {
        if (params == null) {
            return Observable.error(new NullPointerException("Parameter params can't be null"));
        } else if (params.request().parfumeId() <= 0) {
            return Observable.error(new IllegalArgumentException("Parameter parfumeId can't be less than or equals to 0"));
        } else {
            return repository.changeLiked(params.userId(), params.request());
        }
    }

    public static final class Params {

        @NonNull private String token;
        @NonNull private Long userId;
        @NonNull private LikedRequest request;

        public Params(@NonNull String token,
                      @NonNull Long userId,
                      @NonNull LikedRequest request) {
            this.token = token;
            this.userId = userId;
            this.request = request;
        }

        @NonNull
        public String token() {
            return token;
        }

        @NonNull
        public Long userId() {
            return userId;
        }

        @NonNull
        public LikedRequest request() {
            return request;
        }
    }
}
