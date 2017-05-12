package hr.lordsofsmell.parfume.feature.login;

import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.ICore;
import hr.lordsofsmell.parfume.feature.login.usecase.LoginUseCase;

/**
 * Created by tea03 on 5/7/2017.
 */

public interface ILogin {
    interface View extends ICore.View {
        void loginSuccesful(User user);

    }

    interface Presenter extends ICore.Presenter {
        void login(String username,String password);
    }

    interface LoginUseCase extends ICore.Interactor<LoginRequest, User> {
    }


}
