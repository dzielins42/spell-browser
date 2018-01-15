package pl.dzielins42.spells.browser.injection.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.dzielins42.spells.browser.view.spell.list.SpellListActivity;
import pl.dzielins42.spells.browser.view.spell.list.SpellListFragment;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract SpellListActivity bindSpellListActivity();

    @ContributesAndroidInjector
    abstract SpellListFragment bindSpellListFragment();

    // Add bindings for other sub-components here
}
