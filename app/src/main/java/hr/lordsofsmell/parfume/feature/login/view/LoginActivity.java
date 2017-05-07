package hr.lordsofsmell.parfume.feature.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.LoginModule;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.login.ILogin;
import hr.lordsofsmell.parfume.utils.InputUtil;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class LoginActivity extends ActivityView implements ILogin.View {

    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @Inject
    ILogin.Presenter presenter;
    @Inject
    PreferencesUtil preferencesUtil;

    @Override
    public void loginSuccesful(User user) {
        preferencesUtil.persistUser(user);
    }

    @OnClick(R.id.btn_login)
    public void loginClicked() {
        String username = InputUtil.getUsernameText(tilUsername);
        String password = InputUtil.getPasswordText(tilPassword);

        if (username != null && password != null) {
            presenter.login(username, password);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected Unbinder bind() {
        return ButterKnife.bind(this);
    }

    @Override
    protected void init(Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected void injectDependencies(@NonNull AppComponent appComponent) {
        appComponent.plus(new LoginModule(this, getApplicationContext())).inject(this);
    }
}
