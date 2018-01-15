package pl.dzielins42.spells.browser.view.spell.list;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import pl.dzielins42.spells.browser.R;
import pl.dzielins42.spells.browser.data.local.ActivityHelloService;
import pl.dzielins42.spells.browser.data.local.AppHelloService;

public class SpellListActivity
        extends AppCompatActivity
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Inject
    Context injectedCotnext;
    @Inject
    AppHelloService mAppHelloService;
    @Inject
    ActivityHelloService mActivityHelloService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(
                "SpellListActivity",
                "onResume: " + (mAppHelloService != null ? mAppHelloService.hello() : "AppHelloService is null")
        );
        Log.d(
                "SpellListActivity",
                "onResume: " + (mActivityHelloService != null ? mActivityHelloService.hello() : "ActivityHelloService is null")
        );
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

}
