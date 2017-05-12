package hr.lordsofsmell.parfume.feature.login.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.login.ILogin;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

/**
 * Created by tea03 on 5/7/2017.
 */

public class LoginUseCase extends UseCase<LoginRequest, User>
        implements ILogin.LoginUseCase {
    private IRepository repository;

    @Inject
    public LoginUseCase(@NonNull ThreadExecutor threadExecutor,
                        @NonNull PostExecutionThread postExecutionThread,
                        @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<User> createObservable(LoginRequest loginRequest) {
        return null;
    }


}