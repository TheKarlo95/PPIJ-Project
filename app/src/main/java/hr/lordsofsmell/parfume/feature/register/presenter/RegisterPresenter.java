package hr.lordsofsmell.parfume.feature.register.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.ICore;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.register.IRegister;
import hr.lordsofsmell.parfume.utils.UserUtils;

public class RegisterPresenter extends Presenter implements IRegister.Presenter {
    private static final String TAG="Register";
    private IRegister.RegisterUseCase useCase;

    @Inject
    public RegisterPresenter(@NonNull IRegister.View view,
                             @NonNull IRegister.RegisterUseCase useCase) {
        super(view);
        this.useCase = useCase;
    }

    @Override
    public void register(@NonNull String username,
                         @NonNull String password,
                         @NonNull String passwordConfirmation,
                         @NonNull String email,
                         @NonNull String name,
                         @NonNull String surname) {
    final IRegister.View view=(IRegister.View)getView();
        if (password.equals(passwordConfirmation)) {
            RegisterRequest request = RegisterRequest.create(username,
                    UserUtils.hashPassword(password),
                    email,
                    name,
                    surname);
            useCase.execute(request, new Observer<User>(view,TAG, R.string.register_error){
                @Override
                public void onNext(User value){
                    super.onNext(value);
                    view.registrationSuccessful(value);
                }
            });
        } else {
            ((IRegister.View) getView()).passwordError();
        }
    }

    @Override
    protected void cancel() {
        useCase.cancel();
    }
}