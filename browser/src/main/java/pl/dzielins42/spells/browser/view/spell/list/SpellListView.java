package pl.dzielins42.spells.browser.view.spell.list;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.Observable;

public interface SpellListView extends MvpView {
    /**
     * Render state.
     *
     * @param state state to be rendered by the view.
     */
    void render(SpellListState state);

    Observable<Boolean> loadIntent();
}
