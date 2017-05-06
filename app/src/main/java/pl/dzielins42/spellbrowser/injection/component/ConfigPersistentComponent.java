package pl.dzielins42.spellbrowser.injection.component;

import dagger.Component;
import pl.dzielins42.spellbrowser.injection.scope.ConfigPersistent;

@ConfigPersistent
@Component(dependencies = DataComponent.class)
public interface ConfigPersistentComponent {
    ActivityComponent activityComponent();
}
