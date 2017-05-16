package hr.lordsofsmell.parfume.feature.core.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Unbinder;
import hr.lordsofsmell.parfume.AndroidApplication;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.feature.core.ICore;

public abstract class FragmentView extends Fragment implements ICore.View {

    private ICore.Presenter presenter;
    private Dialog dialog;
    private Unbinder unbinder;
    private String tag;

    @LayoutRes
    protected abstract int getLayoutResId();

    @NonNull
    protected abstract String getLogTag();

    protected abstract Unbinder bind(View source);

    protected abstract void init(View view);

    protected abstract void injectDependencies(@NonNull AppComponent appComponent);

    protected <T extends ICore.Presenter> void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        tag = getLogTag();
        Log.i(tag, "onCreateView started");
        injectDependencies(((AndroidApplication) getActivity().getApplication()).getApplicationComponent());

        View view = inflater.inflate(getLayoutResId(), container, false);

        unbinder = bind(view);
        init(view);
        if (savedInstanceState != null) {
            executeOnNonFirstRun(savedInstanceState);
        } else {
            executeOnFirstRun();
        }

        Log.i(tag, "onCreateView finished");
        return view;
    }

    @Override
    public void onResume() {
        Log.i(tag, "onResume started");
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
        Log.i(tag, "onResume finished");
    }

    @Override
    public void onPause() {
        Log.i(tag, "onPause started");
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
        Log.i(tag, "onPause finished");
    }

    @Override
    public void onStop() {
        Log.i(tag, "onStop started");
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
        Log.i(tag, "onStop finished");
    }

    @Override
    public void onDestroyView() {
        Log.i(tag, "onDestroyView started");
        super.onDestroyView();

        hideLoading();
        dialog = null;
        unbinder.unbind();
        Log.i(tag, "onDestroyView finished");
    }

    @Override
    public void onDestroy() {
        Log.i(tag, "onDestroy started");
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
        Log.i(tag, "onDestroy finished");
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
