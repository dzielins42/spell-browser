package pl.dzielins42.spellbrowser.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.dzielins42.spellbrowser.data.DataManager;
import pl.dzielins42.spellbrowser.injection.module.AppModule;
import pl.dzielins42.spellbrowser.injection.module.DataModule;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface DataComponent {
    DataManager dataManager();
}
