package pl.dzielins42.spellbrowser;

import android.app.Application;

import pl.dzielins42.spellbrowser.injection.component.DaggerDataComponent;
import pl.dzielins42.spellbrowser.injection.component.DataComponent;
import pl.dzielins42.spellbrowser.injection.module.AppModule;
import pl.dzielins42.spellbrowser.injection.module.DataModule;

public class DaggerApplication extends Application {

    private DataComponent mDataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataComponent = DaggerDataComponent.builder().appModule(new AppModule(this))
                .dataModule(new DataModule()).build();
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }

}
