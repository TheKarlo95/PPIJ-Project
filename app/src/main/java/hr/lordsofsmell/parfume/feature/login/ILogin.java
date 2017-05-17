package hr.lordsofsmell.parfume.feature.login;

import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.ICore;

public interface ILogin {

    interface View extends ICore.View {
        void loginSuccesful(User user);
    }

    interface Presenter extends ICore.Presenter {
        void login(String username, String password);
    }

    interface LoginUseCase extends ICore.Interactor<LoginRequest, User> {
    }
}
