package pl.dzielins42.spellbrowser.ui.browser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.dzielins42.spellbrowser.DaggerApplication;
import pl.dzielins42.spellbrowser.R;

import pl.dzielins42.masterdetailflowrevised.AbsMasterDetailActivity;
import pl.dzielins42.spellbrowser.data.local.SpellDbService;
import pl.dzielins42.spellbrowser.injection.component.ActivityComponent;
import pl.dzielins42.spellbrowser.injection.component.ConfigPersistentComponent;
import pl.dzielins42.spellbrowser.injection.component.DaggerConfigPersistentComponent;

public class SpellListActivity extends AbsMasterDetailActivity implements View.OnClickListener {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.fab) FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_list);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(getTitle());
        }

        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(mActivityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .dataComponent(((DaggerApplication) getApplication()).getDataComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        } else {
            configPersistentComponent = sComponentsMap.get(mActivityId);
        }

        mActivityComponent = configPersistentComponent.activityComponent();
    }


    @Override
    protected Fragment getListFragment() {
        return new SpellListFragment();
    }

    @Override
    protected Fragment getDetailFragment(String itemId) {
        //   getDetailFragment(long) is used
        throw new UnsupportedOperationException();
    }

    @Override
    protected Fragment getDetailFragment(long itemId) {
        Bundle arguments = new Bundle();
        arguments.putLong(SpellDetailFragment.ARG_ITEM_ID, itemId);
        SpellDetailFragment detailFragment = new SpellDetailFragment();
        detailFragment.setArguments(arguments);

        return detailFragment;
    }

    @Override
    protected int getMainPanelId() {
        return R.id.container_a;
    }

    @Override
    protected int getDetailPanelId() {
        return R.id.container_b;
    }

    @Override
    public void onBackStackChanged() {
        super.onBackStackChanged();
        if (mFab != null) {
            mFab.setVisibility((isItemSelected() && isSinglePaneMode()) ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        if (v.getId() == R.id.fab) {
            onFabClick(v);
        }
    }

    @OnClick(R.id.fab)
    void onFabClick(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction
                ("Action", null).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    protected ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

}