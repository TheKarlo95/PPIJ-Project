package hr.lordsofsmell.parfume.feature.register.usecase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.domain.interactor.UseCase;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.register.IRegister;
import hr.lordsofsmell.parfume.threads.PostExecutionThread;
import hr.lordsofsmell.parfume.threads.ThreadExecutor;
import io.reactivex.Observable;

public class RegisterUseCase extends UseCase<RegisterRequest, User>
        implements IRegister.RegisterUseCase {

    private IRepository repository;

    @Inject
    public RegisterUseCase(@NonNull ThreadExecutor threadExecutor,
                                 @NonNull PostExecutionThread postExecutionThread,
                                 @NonNull IRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<User> createObservable(RegisterRequest r) {
        if (r.email()== null || r.gender()==null || r.name()==null || r.password()==null || r.surname()==null || r.username()==null)  return Observable.error(new NullPointerException("None of the parameters can be null!"));
        else return repository.register(r);
    }


}
