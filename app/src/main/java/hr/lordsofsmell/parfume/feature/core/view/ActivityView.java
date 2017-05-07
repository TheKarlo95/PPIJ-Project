package hr.lordsofsmell.parfume.feature.core.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.Unbinder;
import hr.lordsofsmell.parfume.AndroidApplication;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.feature.core.ICore;

public abstract class ActivityView extends AppCompatActivity implements ICore.View {

    private ICore.Presenter presenter;
    private Dialog dialog;
    private Unbinder unbinder;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract Unbinder bind();

    protected abstract void init(Bundle savedInstanceState, Intent intent);

    protected abstract void injectDependencies(@NonNull AppComponent appComponent);

    protected <T extends ICore.Presenter> void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(((AndroidApplication) getApplication()).getApplicationComponent());
        setContentView(getLayoutResId());

        unbinder = bind();

        Intent intent = getIntent();
        init(savedInstanceState, intent);
        if (savedInstanceState != null) {
            executeOnNonFirstRun(savedInstanceState, intent);
        } else {
            executeOnFirstRun(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null) {
        presenter.onResume();}
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(presenter != null) {
        presenter.onPause();}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(presenter != null) {
        presenter.onStop();}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        hideLoading();
        dialog = null;
        unbinder.unbind();

        if(presenter != null) {
        presenter.onDestroy();}
    }

    @Override
    public void showLoading() {
        if (dialog != null) {
            dialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override
    public void showMessage(@StringRes int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(@StringRes int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show();
    }

    protected void setDialog(@NonNull Dialog dialog) {
        this.dialog = dialog;
    }

    protected void executeOnNonFirstRun(@NonNull Bundle savedInstanceState, Intent intent) {
    }

    protected void executeOnFirstRun(Intent intent) {
    }
}
