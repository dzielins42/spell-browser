package pl.dzielins42.spellbrowser.ui.browser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dzielins42.masterdetailflowrevised.AbsMasterDetailActivity;
import pl.dzielins42.spellbrowser.R;
import pl.dzielins42.spellbrowser.data.model.SourceLevelExtraTriplet;
import pl.dzielins42.spellbrowser.data.model.Spell;
import pl.dzielins42.toolkit.android.widget.CaptionLayout;

public class SpellDetailFragment extends Fragment implements Browser.DetailView {

    private static final String TAG = SpellDetailFragment.class.getSimpleName();

    @Inject
    SpellDetailPresenter mPresenter;

    @BindView(R.id.container_spell_levels)
    ViewGroup mContainerLevels;
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
    @BindView(R.id.cl_spell_levels)
    CaptionLayout mCLLevels;
    @BindView(R.id.tv_spell_components)
    TextView mTVComponents;
    @BindView(R.id.cl_spell_components)
    CaptionLayout mCLComponents;
    @BindView(R.id.tv_spell_casting_time)
    TextView mTVCastingTime;
    @BindView(R.id.cl_spell_casting_time)
    CaptionLayout mCLCastingTime;
    @BindView(R.id.tv_spell_range)
    TextView mTVRange;
    @BindView(R.id.cl_spell_range)
    CaptionLayout mCLRange;
    @BindView(R.id.tv_spell_target)
    TextView mTVTarget;
    @BindView(R.id.cl_spell_target)
    CaptionLayout mCLTarget;
    @BindView(R.id.tv_spell_effect)
    TextView mTVEffect;
    @BindView(R.id.cl_spell_effect)
    CaptionLayout mCLEffect;
    @BindView(R.id.tv_spell_duration)
    TextView mTVDuration;
    @BindView(R.id.cl_spell_duration)
    CaptionLayout mCLDuration;
    @BindView(R.id.tv_spell_saving_throw)
    TextView mTVSavingThrow;
    @BindView(R.id.cl_spell_saving_throw)
    CaptionLayout mCLSavingThrow;
    @BindView(R.id.tv_spell_spell_resistance)
    TextView mTVSpellResistance;
    @BindView(R.id.cl_spell_spell_resistance)
    CaptionLayout mCLSpellResistance;
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
        // Name
        mTVName.setText(data.getName());
        // Schools
        mTVSchools.setText(TextUtils.join(", ", data.getSchools()));
        // Subschools
        if (data.getSubschools() != null && !data.getSubschools().isEmpty()) {
            mTVSubschools.setText("(" + TextUtils.join(", ", data.getSubschools()) + ")");
        } else {
            mTVSubschools.setVisibility(View.GONE);
        }
        // Descriptors
        if (data.getDescriptors() != null && !data.getDescriptors().isEmpty()) {
            mTVDescriptors.setText("[" + TextUtils.join(", ", data.getSubschools()) + "]");
        } else {
            mTVDescriptors.setVisibility(View.GONE);
        }
        // Levels / Sources
        if (data.getSources() != null && !data.getSources().isEmpty()) {

            StringBuilder sb;
            SourceLevelExtraTriplet slet;
            List<String> sources = new ArrayList<>(data.getSources().size());
            for (int i = 0; i < data.getSources().size(); i++) {
                slet = data.getSources().get(i);
                sb = new StringBuilder();
                sb.append(slet.getSource()).append(" ").append(String.valueOf(slet.getLevel()));
                if (!TextUtils.isEmpty(slet.getExtra())) {
                    sb.append(" (").append(slet.getExtra()).append(")");
                }

                sources.add(sb.toString());
            }

            Collections.sort(sources);

            TextView tv;
            for (String source : sources) {
                tv = new TextView(getContext());
                tv.setLayoutParams(mTVLevels.getLayoutParams());
                tv.setText(source);
                mContainerLevels.addView(tv);
                mTVLevels.setVisibility(View.GONE);
            }
        } else {
            mCLLevels.setVisibility(View.GONE);
        }
        // Components
        if (data.getComponents() != null && !data.getComponents().isEmpty()) {
            mTVComponents.setText(TextUtils.join(", ", data.getComponents()));
        } else {
            mTVComponents.setVisibility(View.GONE);
        }
        // Casting Time
        setSectionTextorHide(mCLCastingTime, mTVCastingTime, data.getCastingTime());
        // Range
        setSectionTextorHide(mCLRange, mTVRange, data.getRange());
        // Target
        setSectionTextorHide(mCLTarget, mTVTarget, data.getTarget());
        // Effect
        setSectionTextorHide(mCLEffect, mTVEffect, data.getEffect());
        // Duration
        setSectionTextorHide(mCLDuration, mTVDuration, data.getDuration());
        // Saving Throw
        setSectionTextorHide(mCLSavingThrow, mTVSavingThrow, data.getSavingThrow());
        // Spell Resistance
        setSectionTextorHide(mCLSpellResistance, mTVSpellResistance, data.getSpellResistance());
        //mTVFlavourText.setText();
        // Description
        mTVDescription.setText(Html.fromHtml(data.getDescription().getFormatted()));

        Activity activity = getActivity();
        if (activity != null && activity instanceof AbsMasterDetailActivity) {
            ((AbsMasterDetailActivity) activity).setActionBarTitle(data.getName());
        }
    }

    private void setSectionTextorHide(CaptionLayout sectionLayout, TextView sectionTextView,
            CharSequence text) {
        if (sectionLayout == null) {
            Log.w(TAG, "setSectionTextorHide: sectionLayout is null");
            return;
        }
        if (sectionTextView == null) {
            sectionLayout.setVisibility(View.GONE);
            Log.w(TAG, "setSectionTextorHide: sectionTextView is null");
            return;
        }

        if (!TextUtils.isEmpty(text)) {
            sectionTextView.setText(text);
            sectionLayout.setVisibility(View.VISIBLE);
        } else {
            sectionLayout.setVisibility(View.GONE);
        }
    }

}