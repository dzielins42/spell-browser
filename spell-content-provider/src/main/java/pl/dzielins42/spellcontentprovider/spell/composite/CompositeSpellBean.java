package pl.dzielins42.spellcontentprovider.spell.composite;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.spell.SpellModel;

public class CompositeSpellBean implements SpellModel {
    private long mId;
    private String mName;
    private RulebookBean mRulebook;
    private int mPage;
    private String mCastingTime;
    private String mRange;
    private String mTarget;
    private String mEffect;
    private String mArea;
    private String mDuration;
    private String mSavingThrow;
    private String mSpellResistance;
    private String mDescriptionPlain;
    private String mDescriptionFormatted;
    private String mShortDescriptionPlain;
    private String mShortDescriptionFormatted;
    private String mFlavourTextPlain;
    private String mFlavourTextFormatted;
    private boolean mIsRitual;

    private List<CharacterClassLevelExtraBean> mClassesLevels;
    private List<SchoolSubschoolBean> mSchools;
    private List<ComponentBean> mComponents;
    private List<DescriptorBean> mDescriptors;

    @Override
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @NonNull
    @Override
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        if (name == null) throw new IllegalArgumentException("name must not be null");
        mName = name;
    }

    @NonNull
    public RulebookBean getRulebook() {
        return mRulebook;
    }

    public void setRulebook(@NonNull RulebookBean rulebook) {
        if (rulebook == null) throw new IllegalArgumentException("rulebook must not be null");
        mRulebook = rulebook;
    }

    @Override
    public long getRulebookId() {
        return getRulebook().getId();
    }


    @Override
    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    @NonNull
    @Override
    public String getCastingTime() {
        return mCastingTime;
    }

    public void setCastingTime(@NonNull String castingTime) {
        if (castingTime == null) throw new IllegalArgumentException("castingTime must not be null");
        mCastingTime = castingTime;
    }

    @NonNull
    @Override
    public String getRange() {
        return mRange;
    }

    public void setRange(@NonNull String range) {
        if (range == null) throw new IllegalArgumentException("range must not be null");
        mRange = range;
    }

    @Override
    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        mTarget = target;
    }

    @Override
    public String getEffect() {
        return mEffect;
    }

    public void setEffect(String effect) {
        mEffect = effect;
    }

    @Nullable
    @Override
    public String getArea() {
        return mArea;
    }

    public void setArea(@Nullable String area) {
        mArea = area;
    }

    @NonNull
    @Override
    public String getDuration() {
        return mDuration;
    }

    public void setDuration(@NonNull String duration) {
        if (duration == null) throw new IllegalArgumentException("duration must not be null");
        mDuration = duration;
    }

    @Nullable
    @Override
    public String getSavingThrow() {
        return mSavingThrow;
    }

    public void setSavingThrow(@Nullable String savingThrow) {
        mSavingThrow = savingThrow;
    }

    @Nullable
    @Override
    public String getSpellResistance() {
        return mSpellResistance;
    }

    public void setSpellResistance(@Nullable String spellResistance) {
        mSpellResistance = spellResistance;
    }

    @Nullable
    @Override
    public String getDescriptionPlain() {
        return mDescriptionPlain;
    }

    public void setDescriptionPlain(@Nullable String descriptionPlain) {
        mDescriptionPlain = descriptionPlain;
    }

    @Nullable
    @Override
    public String getDescriptionFormatted() {
        return mDescriptionFormatted;
    }

    public void setDescriptionFormatted(@Nullable String descriptionFormatted) {
        mDescriptionFormatted = descriptionFormatted;
    }

    @Nullable
    @Override
    public String getShortDescriptionPlain() {
        return mShortDescriptionPlain;
    }

    public void setShortDescriptionPlain(@Nullable String shortDescriptionPlain) {
        mShortDescriptionPlain = shortDescriptionPlain;
    }

    @Nullable
    @Override
    public String getShortDescriptionFormatted() {
        return mShortDescriptionFormatted;
    }

    public void setShortDescriptionFormatted(@Nullable String shortDescriptionFormatted) {
        mShortDescriptionFormatted = shortDescriptionFormatted;
    }

    @Nullable
    @Override
    public String getFlavourTextPlain() {
        return mFlavourTextPlain;
    }

    public void setFlavourTextPlain(@Nullable String flavourTextPlain) {
        mFlavourTextPlain = flavourTextPlain;
    }

    @Nullable
    @Override
    public String getFlavourTextFormatted() {
        return mFlavourTextFormatted;
    }

    public void setFlavourTextFormatted(@Nullable String flavourTextFormatted) {
        mFlavourTextFormatted = flavourTextFormatted;
    }

    @Override
    public boolean getIsRitual() {
        return mIsRitual;
    }

    public void setIsRitual(boolean mIsRitual) {
        this.mIsRitual = mIsRitual;
    }

    //TODO nullable or not?

    public List<CharacterClassLevelExtraBean> getClassesLevels() {
        return mClassesLevels;
    }

    public void setClassesLevels(List<CharacterClassLevelExtraBean> classesLevels) {
        this.mClassesLevels = classesLevels;
    }

    public List<SchoolSubschoolBean> getSchools() {
        return mSchools;
    }

    public void setSchools(List<SchoolSubschoolBean> schools) {
        this.mSchools = schools;
    }

    public List<ComponentBean> getComponents() {
        return mComponents;
    }

    public void setComponents(List<ComponentBean> components) {
        this.mComponents = components;
    }

    public List<DescriptorBean> getDescriptors() {
        return mDescriptors;
    }

    public void setDescriptors(List<DescriptorBean> descriptors) {
        this.mDescriptors = descriptors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeSpellBean bean = (CompositeSpellBean) o;
        return mId == bean.mId;
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    @Override
    public String toString() {
        return "CompositeSpellBean{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mRulebook=" + mRulebook +
                ", mPage=" + mPage +
                ", mCastingTime='" + mCastingTime + '\'' +
                ", mRange='" + mRange + '\'' +
                ", mTarget='" + mTarget + '\'' +
                ", mEffect='" + mEffect + '\'' +
                ", mArea='" + mArea + '\'' +
                ", mDuration='" + mDuration + '\'' +
                ", mSavingThrow='" + mSavingThrow + '\'' +
                ", mSpellResistance='" + mSpellResistance + '\'' +
                ", mDescriptionPlain='" + mDescriptionPlain + '\'' +
                ", mDescriptionFormatted='" + mDescriptionFormatted + '\'' +
                ", mShortDescriptionPlain='" + mShortDescriptionPlain + '\'' +
                ", mShortDescriptionFormatted='" + mShortDescriptionFormatted + '\'' +
                ", mFlavourTextPlain='" + mFlavourTextPlain + '\'' +
                ", mFlavourTextFormatted='" + mFlavourTextFormatted + '\'' +
                ", mIsRitual=" + mIsRitual +
                ", mClassesLevels=" + mClassesLevels +
                ", mSchools=" + mSchools +
                ", mComponents=" + mComponents +
                ", mDescriptors=" + mDescriptors +
                '}';
    }

    /**
     * Instantiate a new SpellBean with specified values.
     */
    @NonNull
    public static CompositeSpellBean newInstance(long id, @NonNull String name, @NonNull RulebookBean rulebook, int page, @NonNull String castingTime, @NonNull String range, @NonNull String target, @NonNull String effect, @Nullable String area, @NonNull String duration, @Nullable String savingThrow, @Nullable String spellResistance, @Nullable String descriptionPlain, @Nullable String descriptionFormatted, @Nullable String shortDescriptionPlain, @Nullable String shortDescriptionFormatted, @Nullable String flavourTextPlain, @Nullable String flavourTextFormatted, boolean isRitual) {
        if (name == null) throw new IllegalArgumentException("name must not be null");
        if (rulebook == null) throw new IllegalArgumentException("rulebook must not be null");
        if (castingTime == null) throw new IllegalArgumentException("castingTime must not be null");
        if (range == null) throw new IllegalArgumentException("range must not be null");
        if (target == null) throw new IllegalArgumentException("target must not be null");
        if (effect == null) throw new IllegalArgumentException("effect must not be null");
        if (duration == null) throw new IllegalArgumentException("duration must not be null");
        CompositeSpellBean res = new CompositeSpellBean();
        res.mId = id;
        res.mName = name;
        res.mRulebook = rulebook;
        res.mPage = page;
        res.mCastingTime = castingTime;
        res.mRange = range;
        res.mTarget = target;
        res.mEffect = effect;
        res.mArea = area;
        res.mDuration = duration;
        res.mSavingThrow = savingThrow;
        res.mSpellResistance = spellResistance;
        res.mDescriptionPlain = descriptionPlain;
        res.mDescriptionFormatted = descriptionFormatted;
        res.mShortDescriptionPlain = shortDescriptionPlain;
        res.mShortDescriptionFormatted = shortDescriptionFormatted;
        res.mFlavourTextPlain = flavourTextPlain;
        res.mFlavourTextFormatted = flavourTextFormatted;
        res.mIsRitual = isRitual;
        return res;
    }

    /**
     * Instantiate a new SpellBean with all the values copied from the given model.
     */
    @NonNull
    public static CompositeSpellBean copy(@NonNull CompositeSpellBean from) {
        CompositeSpellBean res = new CompositeSpellBean();
        res.mId = from.getId();
        res.mName = from.getName();
        res.mRulebook = from.getRulebook();
        res.mPage = from.getPage();
        res.mCastingTime = from.getCastingTime();
        res.mRange = from.getRange();
        res.mTarget = from.getTarget();
        res.mEffect = from.getEffect();
        res.mArea = from.getArea();
        res.mDuration = from.getDuration();
        res.mSavingThrow = from.getSavingThrow();
        res.mSpellResistance = from.getSpellResistance();
        res.mDescriptionPlain = from.getDescriptionPlain();
        res.mDescriptionFormatted = from.getDescriptionFormatted();
        res.mShortDescriptionPlain = from.getShortDescriptionPlain();
        res.mShortDescriptionFormatted = from.getShortDescriptionFormatted();
        res.mFlavourTextPlain = from.getFlavourTextPlain();
        res.mFlavourTextFormatted = from.getFlavourTextFormatted();
        res.mIsRitual = from.getIsRitual();
        return res;
    }

    public static class Builder {
        private CompositeSpellBean mRes = new CompositeSpellBean();

        public CompositeSpellBean.Builder id(long id) {
            mRes.mId = id;
            return this;
        }

        public CompositeSpellBean.Builder name(@NonNull String name) {
            if (name == null) throw new IllegalArgumentException("name must not be null");
            mRes.mName = name;
            return this;
        }

        public CompositeSpellBean.Builder rulebook(@NonNull RulebookBean rulebook) {
            if (rulebook == null) throw new IllegalArgumentException("rulebook must not be null");
            mRes.mRulebook = rulebook;
            return this;
        }

        public CompositeSpellBean.Builder page(int page) {
            mRes.mPage = page;
            return this;
        }

        public CompositeSpellBean.Builder castingTime(@NonNull String castingTime) {
            if (castingTime == null)
                throw new IllegalArgumentException("castingTime must not be null");
            mRes.mCastingTime = castingTime;
            return this;
        }

        public CompositeSpellBean.Builder range(@NonNull String range) {
            if (range == null) throw new IllegalArgumentException("range must not be null");
            mRes.mRange = range;
            return this;
        }

        public CompositeSpellBean.Builder target(@NonNull String target) {
            if (target == null) throw new IllegalArgumentException("target must not be null");
            mRes.mTarget = target;
            return this;
        }

        public CompositeSpellBean.Builder effect(@NonNull String effect) {
            if (effect == null) throw new IllegalArgumentException("effect must not be null");
            mRes.mEffect = effect;
            return this;
        }

        public CompositeSpellBean.Builder area(@Nullable String area) {
            mRes.mArea = area;
            return this;
        }

        public CompositeSpellBean.Builder duration(@NonNull String duration) {
            if (duration == null) throw new IllegalArgumentException("duration must not be null");
            mRes.mDuration = duration;
            return this;
        }

        public CompositeSpellBean.Builder savingThrow(@Nullable String savingThrow) {
            mRes.mSavingThrow = savingThrow;
            return this;
        }

        public CompositeSpellBean.Builder spellResistance(@Nullable String spellResistance) {
            mRes.mSpellResistance = spellResistance;
            return this;
        }

        public CompositeSpellBean.Builder descriptionPlain(@Nullable String descriptionPlain) {
            mRes.mDescriptionPlain = descriptionPlain;
            return this;
        }

        public CompositeSpellBean.Builder descriptionFormatted(@Nullable String descriptionFormatted) {
            mRes.mDescriptionFormatted = descriptionFormatted;
            return this;
        }

        public CompositeSpellBean.Builder shortDescriptionPlain(@Nullable String shortDescriptionPlain) {
            mRes.mShortDescriptionPlain = shortDescriptionPlain;
            return this;
        }

        public CompositeSpellBean.Builder shortDescriptionFormatted(@Nullable String shortDescriptionFormatted) {
            mRes.mShortDescriptionFormatted = shortDescriptionFormatted;
            return this;
        }

        public CompositeSpellBean.Builder flavourTextPlain(@Nullable String flavourTextPlain) {
            mRes.mFlavourTextPlain = flavourTextPlain;
            return this;
        }

        public CompositeSpellBean.Builder flavourTextFormatted(@Nullable String flavourTextFormatted) {
            mRes.mFlavourTextFormatted = flavourTextFormatted;
            return this;
        }

        public CompositeSpellBean.Builder isRitual(boolean isRitual) {
            mRes.mIsRitual = isRitual;
            return this;
        }

        public CompositeSpellBean build() {
            if (mRes.mName == null) throw new IllegalArgumentException("name must not be null");
            if (mRes.mRulebook == null)
                throw new IllegalArgumentException("rulebook must not be null");
            if (mRes.mCastingTime == null)
                throw new IllegalArgumentException("castingTime must not be null");
            if (mRes.mRange == null) throw new IllegalArgumentException("range must not be null");
            if (mRes.mTarget == null) throw new IllegalArgumentException("target must not be null");
            if (mRes.mEffect == null) throw new IllegalArgumentException("effect must not be null");
            if (mRes.mDuration == null)
                throw new IllegalArgumentException("duration must not be null");
            return mRes;
        }
    }

    public static CompositeSpellBean.Builder newBuilder() {
        return new CompositeSpellBean.Builder();
    }
}