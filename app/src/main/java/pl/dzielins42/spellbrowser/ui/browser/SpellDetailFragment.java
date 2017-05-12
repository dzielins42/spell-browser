package pl.dzielins42.spellbrowser.ui.browser;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dzielins42.masterdetailflowrevised.AbsMasterDetailActivity;
import pl.dzielins42.spellbrowser.R;
import pl.dzielins42.spellbrowser.data.model.Spell;

public class SpellDetailFragment extends Fragment implements Browser.DetailView{

    @Inject
    SpellDetailPresenter mPresenter;

    @BindView(R.id.spell_detail)
    TextView mSpellDetail;

    public static final String ARG_ITEM_ID = "item_id";

    private Long mItemId = null;

    public SpellDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItemId = getArguments().getLong(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inject and attach to Presenter
        ((SpellListActivity) getActivity()).getActivityComponent().inject(this);
        mPresenter.attachView(this);

        // Create and bind layout
        View rootView = inflater.inflate(R.layout.fragment_spell_detail, container, false);
        ButterKnife.bind(this, rootView);

        mPresenter.loadSpell(mItemId);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();

        super.onDestroyView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setData(Spell data) {
        mSpellDetail.setText(data.getName());

        Activity activity = getActivity();
        if (activity != null && activity instanceof AbsMasterDetailActivity) {
            ((AbsMasterDetailActivity) activity).setActionBarTitle(data.getName());
        }
    }
}