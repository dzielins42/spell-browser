package pl.dzielins42.spells.browser.injection.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import pl.dzielins42.spells.browser.BrowserApplication;
import pl.dzielins42.spells.browser.injection.module.AppModule;
import pl.dzielins42.spells.browser.injection.module.BuildersModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, BuildersModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(BrowserApplication application);

}
