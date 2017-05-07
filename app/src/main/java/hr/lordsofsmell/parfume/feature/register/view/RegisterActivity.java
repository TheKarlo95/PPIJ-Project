package hr.lordsofsmell.parfume.feature.register.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.register.IRegister;

public class RegisterActivity extends ActivityView
        implements IRegister.View {

    @Inject
    IRegister.Presenter presenter;

    public static Intent createIntent(Context context, int listType) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    public void registrationSuccessful(User user) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
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
        //     appComponent.plus(new RegisterModule(this, getApplicationContext())).inject(this);
    }

    @Override
    public void passwordError() {

    }
}