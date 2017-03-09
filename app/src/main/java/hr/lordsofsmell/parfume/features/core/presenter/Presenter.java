package hr.lordsofsmell.parfume.features.core.presenter;

import android.support.annotation.NonNull;

import hr.lordsofsmell.parfume.features.core.ICore;

public abstract class Presenter<T extends ICore.View> implements ICore.Presenter<T> {

    private ICore.View view;

    public Presenter(@NonNull T view) {
        this.view = view;
    }

    @Override
    public void onStop() {
        view.hideLoading();
    }

    protected ICore.View getView() {
        return view;
    }
}
