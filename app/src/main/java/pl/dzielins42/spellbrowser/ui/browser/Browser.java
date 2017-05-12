package pl.dzielins42.spellbrowser.ui.browser;

import java.util.List;

import pl.dzielins42.spellbrowser.data.model.Spell;
import pl.dzielins42.spellbrowser.ui.base.Mvp;

interface Browser {

    interface ListView extends Mvp.LceView<List<Spell>> {
    }

    interface DetailView extends Mvp.LceView<Spell> {
    }

    interface ListPresenter extends Mvp.Presenter<Browser.ListView> {
        void loadSpells();
    }

    interface DetailPresenter extends Mvp.Presenter<Browser.DetailView> {
        void loadSpell(Long id);
    }

}
