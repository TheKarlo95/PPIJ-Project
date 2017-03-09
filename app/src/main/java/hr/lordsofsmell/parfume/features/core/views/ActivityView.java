package hr.lordsofsmell.parfume.features.core.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import hr.lordsofsmell.parfume.AndroidApplication;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.features.core.ICore;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
public abstract class ActivityView extends Activity implements ICore.View {

    private Dialog dialog;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void init();

    protected abstract void injectDependencies(@NonNull AppComponent appComponent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(((AndroidApplication) getApplication()).getApplicationComponent());
        setContentView(getLayoutResId());

        init();
        if (savedInstanceState != null) {
            executeOnNonFirstRun(savedInstanceState);
        } else {
            executeOnFirstRun();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dialog.hide();
        dialog = null;
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

    protected void setDialog(@NonNull Dialog dialog) {
        this.dialog = dialog;
    }

    protected void executeOnNonFirstRun(@NonNull Bundle savedInstanceState) {
    }

    protected void executeOnFirstRun() {
    }
}
