package pl.dzielins42.spellbrowser.ui.browser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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

    @BindView(R.id.tv_spell_name)
    TextView mTVName;
    @BindView(R.id.tv_spell_schools)
    TextView mTVSchools;
    @BindView(R.id.tv_spell_subschools)
    TextView mTVSubschools;
    @BindView(R.id.tv_spell_descriptors)
    TextView mTVDescriptors;
    @BindView(R.id.tv_spell_levels)
    TextView mTVLevels;
    @BindView(R.id.tv_spell_components)
    TextView mTVComponents;
    @BindView(R.id.tv_spell_casting_time)
    TextView mTVCastingTime;
    @BindView(R.id.tv_spell_range)
    TextView mTVRange;
    @BindView(R.id.tv_spell_target)
    TextView mTVTarget;
    @BindView(R.id.tv_spell_effect)
    TextView mTVEffect;
    @BindView(R.id.tv_spell_duration)
    TextView mTVDuration;
    @BindView(R.id.tv_spell_saving_throw)
    TextView mTVSavingThrow;
    @BindView(R.id.tv_spell_spell_resistance)
    TextView mTVSpellResistance;
    @BindView(R.id.tv_spell_spell_flavour_text)
    TextView mTVFlavourText;
    @BindView(R.id.tv_spell_description)
    TextView mTVDescription;


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
        mTVName.setText(data.getName());
        //mTVSchools.setText();
        //mTVSubschools.setText();
        //mTVDescriptors.setText();
        //mTVLevels.setText();
        //mTVComponents.setText();
        mTVCastingTime.setText(data.getCastingTime());
        mTVRange.setText(data.getRange());
        mTVTarget.setText(data.getTarget());
        mTVEffect.setText(data.getEffect());
        mTVDuration.setText(data.getDuration());
        mTVSavingThrow.setText(data.getSavingThrow());
        mTVSpellResistance.setText(data.getSpellResistance());
        //mTVFlavourText.setText();
        mTVDescription.setText(Html.fromHtml(data.getDescription().getFormatted()));

        Activity activity = getActivity();
        if (activity != null && activity instanceof AbsMasterDetailActivity) {
            ((AbsMasterDetailActivity) activity).setActionBarTitle(data.getName());
        }
    }
}