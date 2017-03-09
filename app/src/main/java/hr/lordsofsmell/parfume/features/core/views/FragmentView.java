package hr.lordsofsmell.parfume.features.core.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hr.lordsofsmell.parfume.AndroidApplication;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.features.core.ICore;

/**
 * Created by Karlo Vrbic on 09.03.17.
 */
public abstract class FragmentView extends Fragment implements ICore.View {

    private Dialog dialog;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void init(View view);

    protected abstract void injectDependencies(@NonNull AppComponent appComponent);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injectDependencies(((AndroidApplication) getActivity().getApplication()).getApplicationComponent());

        View view = inflater.inflate(getLayoutResId(), container, false);
        init(view);

        if (savedInstanceState != null) {
            executeOnNonFirstRun(savedInstanceState);
        } else {
            executeOnFirstRun();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        hideLoading();
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
        Toast.makeText(getContext(), getString(messageId), Toast.LENGTH_LONG).show();
    }

    protected void setDialog(@NonNull Dialog dialog) {
        this.dialog = dialog;
    }

    protected void executeOnNonFirstRun(@NonNull Bundle savedInstanceState) {
    }

    protected void executeOnFirstRun() {
    }
}
