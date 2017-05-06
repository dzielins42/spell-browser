package pl.dzielins42.spellbrowser.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

public interface Mvp {

    interface View {
    }

    interface LceView<D> extends View {
        @UiThread
        void showLoading();

        @UiThread
        void showContent();

        @UiThread
        void showError();

        @UiThread
        void setData(D data);
    }

    interface Presenter<V extends Mvp.View> {
        @UiThread
        void attachView(@NonNull V view);

        @UiThread
        void detachView();
    }

}
