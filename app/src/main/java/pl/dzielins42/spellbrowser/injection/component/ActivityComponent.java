package pl.dzielins42.spellbrowser.injection.component;

import dagger.Subcomponent;
import pl.dzielins42.spellbrowser.injection.scope.PerActivity;
import pl.dzielins42.spellbrowser.ui.browser.SpellDetailFragment;
import pl.dzielins42.spellbrowser.ui.browser.SpellListFragment;
import pl.dzielins42.spellbrowser.ui.main.MainActivity;

@PerActivity
@Subcomponent()
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject (SpellListFragment fragment);
    void inject (SpellDetailFragment fragment);
}
