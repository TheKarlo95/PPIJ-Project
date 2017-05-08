package hr.lordsofsmell.parfume.feature.perfumelist.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class ChangeWishlistedUseCase extends UseCase<ChangeWishlistedUseCase.Params, Void>
        implements IPerfumeList.ChangeWishlistedUseCase {

    private IRepository repository;

    @Inject
    ChangeWishlistedUseCase(@NonNull ThreadExecutor threadExecutor,
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
            return repository.changeWishlisted(params.userId(), params.request());
        }
    }

    public static final class Params {

        @NonNull private String token;
        @NonNull private Long userId;
        @NonNull private WishlistRequest request;

        public Params(@NonNull String token,
                      @NonNull Long userId,
                      @NonNull WishlistRequest request) {
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
        public WishlistRequest request() {
            return request;
        }
    }
}
