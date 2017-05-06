package pl.dzielins42.spellbrowser.ui.base;


import android.support.annotation.NonNull;

public class BasePresenter<V extends Mvp.View> implements Mvp.Presenter<V> {

    private V mView;

    @Override
    public void attachView(@NonNull V view) {
        if (view == null) {
            throw new IllegalArgumentException(
                    "view cannot be null! call detachView() to detach Presenter from View.");
        }

        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

}
