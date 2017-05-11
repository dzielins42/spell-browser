package pl.dzielins42.spellbrowser.data.model;

import java.util.List;

public class Spell {

    private Long mId;
    private String mName;
    private String mRulebook;
    private Integer mPage;
    private List<String> mSchools;
    private List<String> mSubschools;
    private List<String> mComponents;
    private String mCastingTime;
    private String mRange;
    private String mTarget;
    private String mEffect;
    private String mArea;
    private String mDuration;
    private String mSavingThrow;
    private String mSpellResistance;
    private FormattedString mDescription;
    private FormattedString mShortDescription;
    private FormattedString mFlavourText;
    private List<String> mDescriptors;
    private List<SourceLevelExtraTriplet> mSources;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getRulebook() {
        return mRulebook;
    }

    public void setRulebook(String rulebook) {
        this.mRulebook = rulebook;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        this.mPage = page;
    }

    public List<String> getSchools() {
        return mSchools;
    }

    public void setSchools(List<String> schools) {
        this.mSchools = schools;
    }

    public List<String> getSubschools() {
        return mSubschools;
    }

    public void setSubschools(List<String> subschools) {
        this.mSubschools = subschools;
    }

    public List<String> getComponents() {
        return mComponents;
    }

    public void setComponents(List<String> components) {
        this.mComponents = components;
    }

    public String getCastingTime() {
        return mCastingTime;
    }

    public void setCastingTime(String castingTime) {
        this.mCastingTime = castingTime;
    }

    public String getRange() {
        return mRange;
    }

    public void setRange(String range) {
        this.mRange = range;
    }

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        this.mTarget = target;
    }

    public String getEffect() {
        return mEffect;
    }

    public void setEffect(String effect) {
        this.mEffect = effect;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String area) {
        this.mArea = area;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    public String getSavingThrow() {
        return mSavingThrow;
    }

    public void setSavingThrow(String savingThrow) {
        this.mSavingThrow = savingThrow;
    }

    public String getSpellResistance() {
        return mSpellResistance;
    }

    public void setSpellResistance(String spellResistance) {
        this.mSpellResistance = spellResistance;
    }

    public FormattedString getDescription() {
        return mDescription;
    }

    public void setDescription(FormattedString description) {
        this.mDescription = description;
    }

    public FormattedString getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(FormattedString shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public FormattedString getFlavourText() {
        return mFlavourText;
    }

    public void setFlavourText(FormattedString flavourText) {
        this.mFlavourText = flavourText;
    }

    public List<String> getDescriptors() {
        return mDescriptors;
    }

    public void setDescriptors(List<String> descriptors) {
        this.mDescriptors = descriptors;
    }

    public List<SourceLevelExtraTriplet> getSources() {
        return mSources;
    }

    public void setSources(List<SourceLevelExtraTriplet> sources) {
        this.mSources = sources;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mRulebook='" + mRulebook + '\'' +
                ", mPage=" + mPage +
                ", mSchools=" + mSchools +
                ", mSubschools=" + mSubschools +
                ", mComponents=" + mComponents +
                ", mCastingTime='" + mCastingTime + '\'' +
                ", mRange='" + mRange + '\'' +
                ", mTarget='" + mTarget + '\'' +
                ", mEffect='" + mEffect + '\'' +
                ", mArea='" + mArea + '\'' +
                ", mDuration='" + mDuration + '\'' +
                ", mSavingThrow='" + mSavingThrow + '\'' +
                ", mSpellResistance='" + mSpellResistance + '\'' +
                ", mDescription=" + mDescription +
                ", mShortDescription=" + mShortDescription +
                ", mFlavourText=" + mFlavourText +
                ", mDescriptors=" + mDescriptors +
                ", mSources=" + mSources +
                '}';
    }

}
