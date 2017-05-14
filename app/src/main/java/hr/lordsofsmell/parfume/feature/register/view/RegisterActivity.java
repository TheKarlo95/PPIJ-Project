package hr.lordsofsmell.parfume.feature.register.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.RadioGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.RegisterModule;
import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.perfumelist.view.PerfumeListActivity;
import hr.lordsofsmell.parfume.feature.register.IRegister;
import hr.lordsofsmell.parfume.utils.InputUtil;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class RegisterActivity extends ActivityView
        implements IRegister.View {

    private static final String TAG = "Register";

    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.til_password_confirmation)
    TextInputLayout tilPasswordConfirmation;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_surname)
    TextInputLayout tilSurname;
    @BindView(R.id.rg_gender)
    RadioGroup rgGender;

    @Inject
    IRegister.Presenter presenter;

    public static Intent createIntent(Context context, int listType) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra(PerfumeListActivity.EXTRA_LIST_TYPE, listType);
        return intent;
    }

    @Override
    public void registrationSuccessful(User user) {
        PreferencesUtil.persistUser(user);

        Intent intent = getIntent();

        if (intent == null) {
            startActivity(PerfumeListActivity.createIntent(this,
                    PerfumeListActivity.LIST_TYPE_ALL_PERFUMES));
        } else {
            int listType = intent.getIntExtra(PerfumeListActivity.EXTRA_LIST_TYPE,
                    PerfumeListActivity.LIST_TYPE_ERROR);

            startActivity(PerfumeListActivity.createIntent(this, listType));
        }
    }

    @OnClick(R.id.btn_register)
    public void registerClicked() {
        String username = InputUtil.getUsernameText(tilUsername);
        String password = InputUtil.getPasswordText(tilPassword);
        String passwordConfirmation = InputUtil.getPasswordConfiramtionText(tilPasswordConfirmation);
        String email = InputUtil.getEmailText(tilEmail);
        String name = InputUtil.getNameText(tilName);
        String surname = InputUtil.getSurnameText(tilSurname);
        Gender gender = InputUtil.getGender(rgGender);

        if (!username.equals(InputUtil.EMPTY)
                && !password.equals(InputUtil.EMPTY)
                && !passwordConfirmation.equals(InputUtil.EMPTY)
                && !email.equals(InputUtil.EMPTY)
                && !name.equals(InputUtil.EMPTY)
                && !surname.equals(InputUtil.EMPTY)) {
            presenter.register(username,
                    password,
                    passwordConfirmation,
                    email,
                    name,
                    surname,
                    gender);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    @NonNull
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected Unbinder bind() {
        return ButterKnife.bind(this);
    }

    @Override
    protected void init(Bundle savedInstanceState, Intent intent) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void injectDependencies(@NonNull AppComponent appComponent) {
        appComponent.plus(new RegisterModule(this, getApplicationContext())).inject(this);
    }

    @Override
    public void passwordError() {

    }
}