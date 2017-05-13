package hr.lordsofsmell.parfume.feature.register.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.RegisterModule;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.login.view.LoginActivity;
import hr.lordsofsmell.parfume.feature.perfumelist.view.PerfumeListActivity;
import hr.lordsofsmell.parfume.feature.register.IRegister;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class RegisterActivity extends ActivityView
        implements IRegister.View {

    private static final String TAG = "PerfumeList";

    @Inject
    IRegister.Presenter presenter;

    public static Intent createIntent(Context context, int listType) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra(PerfumeListActivity.EXTRA_LIST_TYPE, listType);
        return intent;
    }

    public static Intent createIntent(Context context, int listType, int action, int position) {
        Intent intent = createIntent(context, listType);
        intent.putExtra(PerfumeListActivity.EXTRA_ACTION, action);
        intent.putExtra(PerfumeListActivity.EXTRA_POSITION, position);
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
            int action = intent.getIntExtra(PerfumeListActivity.EXTRA_ACTION,
                    PerfumeListActivity.ACTION_ERROR);
            int position = intent.getIntExtra(PerfumeListActivity.EXTRA_POSITION,
                    PerfumeListActivity.POSITION_ERROR);

            startActivity(PerfumeListActivity.createIntent(this, listType, action, position));
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