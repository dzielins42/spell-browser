package pl.dzielins42.spellbrowser.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import pl.dzielins42.spellbrowser.R;
import pl.dzielins42.spellbrowser.data.DataManager;
import pl.dzielins42.spellbrowser.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Inject
    DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
    }
}
