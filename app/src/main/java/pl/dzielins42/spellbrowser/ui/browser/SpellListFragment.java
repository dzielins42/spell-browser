package pl.dzielins42.spellbrowser.ui.browser;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dzielins42.masterdetailflowrevised.AbsMasterDetailActivity;
import pl.dzielins42.spellbrowser.R;
import pl.dzielins42.spellbrowser.data.model.Spell;
import pl.dzielins42.toolkit.android.support.v7.widget.CompoundRecyclerView;

public class SpellListFragment extends Fragment implements Browser.ListView,
        SpellListAdapter.SpellListAdapterListener {

    @Inject
    SpellListPresenter mPresenter;

    private SpellListAdapter mAdapter;

    @BindView(R.id.spell_list)
    CompoundRecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inject and attach to Presenter
        ((SpellListActivity) getActivity()).getActivityComponent().inject(this);
        mPresenter.attachView(this);

        // Create and bind layout
        View rootView = inflater.inflate(R.layout.fragment_spell_list, container, false);
        ButterKnife.bind(this, rootView);
        // Setup layout
        setupRecyclerView(mRecyclerView);
        mPresenter.loadSpells();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();

        super.onDestroyView();
    }

    private void setupRecyclerView(@NonNull CompoundRecyclerView recyclerView) {
        // Set adapter...
        mAdapter = new SpellListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.clear();
        // ...and hide it immediately
        recyclerView.setRecyclerShownNoAnimation(false);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRecyclerShown(false);
    }

    @Override
    public void showContent() {
        mRecyclerView.setRecyclerShown(true);
    }

    @Override
    public void showError() {

    }

    @Override
    public void setData(List<Spell> data) {
        mAdapter.clear();
        mAdapter.addAll(data);
    }

    @Override
    public void onItemClicked(Spell item) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof AbsMasterDetailActivity) {
            ((AbsMasterDetailActivity) getActivity()).onItemSelected(item.getId());
        }
    }

}