package hr.lordsofsmell.parfume.feature.core.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import butterknife.Unbinder;
import hr.lordsofsmell.parfume.AndroidApplication;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.feature.core.ICore;

public abstract class ActivityView extends AppCompatActivity implements ICore.View {

    private ICore.Presenter presenter;
    private ProgressDialog dialog;
    private Unbinder unbinder;
    private String tag;

    @LayoutRes
    protected abstract int getLayoutResId();

    @NonNull
    protected abstract String getLogTag();

    protected abstract Unbinder bind();

    protected abstract void init(Bundle savedInstanceState, Intent intent);

    protected abstract void injectDependencies(@NonNull AppComponent appComponent);

    protected <T extends ICore.Presenter> void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tag = getLogTag();
        Log.i(tag, "onCreate started");
        super.onCreate(savedInstanceState);
        injectDependencies(((AndroidApplication) getApplication()).getApplicationComponent());
        setContentView(getLayoutResId());

        initProgressDialog();

        unbinder = bind();

        Intent intent = getIntent();
        init(savedInstanceState, intent);
        Log.i(tag, "onCreate finished");
    }

    @Override
    protected void onResume() {
        Log.i(tag, "onResume started");
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
        Log.i(tag, "onResume finished");
    }

    @Override
    protected void onPause() {
        Log.i(tag, "onPause started");
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
        Log.i(tag, "onPause finished");
    }

    @Override
    protected void onStop() {
        Log.i(tag, "onStop started");
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
        Log.i(tag, "onStop finished");
    }

    @Override
    protected void onDestroy() {
        Log.i(tag, "onDestroy started");
        super.onDestroy();

        hideLoading();
        dialog = null;
        unbinder.unbind();

        if (presenter != null) {
            presenter.onDestroy();
        }
        Log.i(tag, "onDestroy finished");
    }

    @Override
    public void showLoading() {
        if (dialog != null) {
            dialog.show();
            Log.i(tag, "Dialog shown");
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.hide();
            Log.i(tag, "Dialog hidden");
        }
    }

    @Override
    public void showError(@StringRes int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show();
    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.loading_message));
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
    }
}
