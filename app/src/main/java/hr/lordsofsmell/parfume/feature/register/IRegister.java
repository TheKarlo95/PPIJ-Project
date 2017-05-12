package hr.lordsofsmell.parfume.feature.register;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.ICore;

public interface IRegister extends ICore {
    interface View extends ICore.View {
        void registrationSuccessful(User user);
        void passwordError();
    }

    interface Presenter extends ICore.Presenter {
        void register(@NonNull String username,
                      @NonNull String password,
                      @NonNull String passwordConfirmation,
                      @NonNull String email,
                      @NonNull String name,
                      @NonNull String surname,
                      @NonNull Gender gender);
    }
    interface RegisterUseCase extends ICore.Interactor<RegisterRequest, User> {
    }
}
