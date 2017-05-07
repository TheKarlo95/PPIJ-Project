package hr.lordsofsmell.parfume.feature.core.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import hr.lordsofsmell.parfume.feature.core.ICore;

public abstract class Presenter implements ICore.Presenter {

    private ICore.View view;

    public Presenter(@NonNull ICore.View view) {
        this.view = view;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onError(@StringRes int stringId) {
        view.showError(stringId);
    }

    protected ICore.View getView() {
        return view;
    }
}
