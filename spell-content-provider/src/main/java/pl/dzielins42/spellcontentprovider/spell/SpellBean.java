package pl.dzielins42.spellcontentprovider.spell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SpellBean implements SpellModel {
    private long mId;
    private long mSpellId;
    private String mSpellName;
    private long mRulebookId;
    private String mRulebookName;
    private int mSpellPage;
    private String mSpellCastingTime;
    private String mSpellRange;
    private String mSpellTarget;
    private String mSpellEffect;
    private String mSpellArea;
    private String mSpellDuration;
    private String mSpellSavingThrow;
    private String mSpellSpellResistance;
    private String mSpellDescriptionPlain;
    private String mSpellDescriptionFormatted;
    private String mSpellShortDescriptionPlain;
    private String mSpellShortDescriptionFormatted;
    private String mSpellFlavourTextPlain;
    private String mSpellFlavourTextFormatted;
    private boolean mSpellIsRitual;
    private long mCharacterClassId;
    private String mCharacterClassName;
    private int mCharacterClassLevel;
    private String mCharacterClassExtra;
    private long mComponentId;
    private String mComponentName;
    private long mDescriptorId;
    private String mDescriptorName;
    private long mSchoolId;
    private String mSchoolName;
    private long mSubschoolId;
    private String mSubschoolName;

    /**
     * Primary key.
     */
    public long getId() {
        return mId;
    }

    /**
     * Primary key.
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * Get the {@code spell__id} value.
     */
    public long getSpellId() {
        return mSpellId;
    }

    /**
     * Set the {@code spell__id} value.
     */
    public void setSpellId(long spellId) {
        mSpellId = spellId;
    }

    /**
     * Get the {@code spell_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSpellName() {
        return mSpellName;
    }

    /**
     * Set the {@code spell_name} value.
     * Must not be {@code null}.
     */
    public void setSpellName(@NonNull String spellName) {
        if (spellName == null) throw new IllegalArgumentException("spellName must not be null");
        mSpellName = spellName;
    }

    /**
     * Get the {@code rulebook__id} value.
     */
    public long getRulebookId() {
        return mRulebookId;
    }

    /**
     * Set the {@code rulebook__id} value.
     */
    public void setRulebookId(long rulebookId) {
        mRulebookId = rulebookId;
    }

    /**
     * Get the {@code rulebook_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getRulebookName() {
        return mRulebookName;
    }

    /**
     * Set the {@code rulebook_name} value.
     * Must not be {@code null}.
     */
    public void setRulebookName(@NonNull String rulebookName) {
        if (rulebookName == null) throw new IllegalArgumentException("rulebookName must not be null");
        mRulebookName = rulebookName;
    }

    /**
     * Get the {@code spell_page} value.
     */
    public int getSpellPage() {
        return mSpellPage;
    }

    /**
     * Set the {@code spell_page} value.
     */
    public void setSpellPage(int spellPage) {
        mSpellPage = spellPage;
    }

    /**
     * Get the {@code spell_casting_time} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSpellCastingTime() {
        return mSpellCastingTime;
    }

    /**
     * Set the {@code spell_casting_time} value.
     * Must not be {@code null}.
     */
    public void setSpellCastingTime(@NonNull String spellCastingTime) {
        if (spellCastingTime == null) throw new IllegalArgumentException("spellCastingTime must not be null");
        mSpellCastingTime = spellCastingTime;
    }

    /**
     * Get the {@code spell_range} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSpellRange() {
        return mSpellRange;
    }

    /**
     * Set the {@code spell_range} value.
     * Must not be {@code null}.
     */
    public void setSpellRange(@NonNull String spellRange) {
        if (spellRange == null) throw new IllegalArgumentException("spellRange must not be null");
        mSpellRange = spellRange;
    }

    /**
     * Get the {@code spell_target} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSpellTarget() {
        return mSpellTarget;
    }

    /**
     * Set the {@code spell_target} value.
     * Must not be {@code null}.
     */
    public void setSpellTarget(@NonNull String spellTarget) {
        if (spellTarget == null) throw new IllegalArgumentException("spellTarget must not be null");
        mSpellTarget = spellTarget;
    }

    /**
     * Get the {@code spell_effect} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSpellEffect() {
        return mSpellEffect;
    }

    /**
     * Set the {@code spell_effect} value.
     * Must not be {@code null}.
     */
    public void setSpellEffect(@NonNull String spellEffect) {
        if (spellEffect == null) throw new IllegalArgumentException("spellEffect must not be null");
        mSpellEffect = spellEffect;
    }

    /**
     * Get the {@code spell_area} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellArea() {
        return mSpellArea;
    }

    /**
     * Set the {@code spell_area} value.
     * Can be {@code null}.
     */
    public void setSpellArea(@Nullable String spellArea) {
        mSpellArea = spellArea;
    }

    /**
     * Get the {@code spell_duration} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSpellDuration() {
        return mSpellDuration;
    }

    /**
     * Set the {@code spell_duration} value.
     * Must not be {@code null}.
     */
    public void setSpellDuration(@NonNull String spellDuration) {
        if (spellDuration == null) throw new IllegalArgumentException("spellDuration must not be null");
        mSpellDuration = spellDuration;
    }

    /**
     * Get the {@code spell_saving_throw} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellSavingThrow() {
        return mSpellSavingThrow;
    }

    /**
     * Set the {@code spell_saving_throw} value.
     * Can be {@code null}.
     */
    public void setSpellSavingThrow(@Nullable String spellSavingThrow) {
        mSpellSavingThrow = spellSavingThrow;
    }

    /**
     * Get the {@code spell_spell_resistance} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellSpellResistance() {
        return mSpellSpellResistance;
    }

    /**
     * Set the {@code spell_spell_resistance} value.
     * Can be {@code null}.
     */
    public void setSpellSpellResistance(@Nullable String spellSpellResistance) {
        mSpellSpellResistance = spellSpellResistance;
    }

    /**
     * Get the {@code spell_description_plain} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellDescriptionPlain() {
        return mSpellDescriptionPlain;
    }

    /**
     * Set the {@code spell_description_plain} value.
     * Can be {@code null}.
     */
    public void setSpellDescriptionPlain(@Nullable String spellDescriptionPlain) {
        mSpellDescriptionPlain = spellDescriptionPlain;
    }

    /**
     * Get the {@code spell_description_formatted} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellDescriptionFormatted() {
        return mSpellDescriptionFormatted;
    }

    /**
     * Set the {@code spell_description_formatted} value.
     * Can be {@code null}.
     */
    public void setSpellDescriptionFormatted(@Nullable String spellDescriptionFormatted) {
        mSpellDescriptionFormatted = spellDescriptionFormatted;
    }

    /**
     * Get the {@code spell_short_description_plain} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellShortDescriptionPlain() {
        return mSpellShortDescriptionPlain;
    }

    /**
     * Set the {@code spell_short_description_plain} value.
     * Can be {@code null}.
     */
    public void setSpellShortDescriptionPlain(@Nullable String spellShortDescriptionPlain) {
        mSpellShortDescriptionPlain = spellShortDescriptionPlain;
    }

    /**
     * Get the {@code spell_short_description_formatted} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellShortDescriptionFormatted() {
        return mSpellShortDescriptionFormatted;
    }

    /**
     * Set the {@code spell_short_description_formatted} value.
     * Can be {@code null}.
     */
    public void setSpellShortDescriptionFormatted(@Nullable String spellShortDescriptionFormatted) {
        mSpellShortDescriptionFormatted = spellShortDescriptionFormatted;
    }

    /**
     * Get the {@code spell_flavour_text_plain} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellFlavourTextPlain() {
        return mSpellFlavourTextPlain;
    }

    /**
     * Set the {@code spell_flavour_text_plain} value.
     * Can be {@code null}.
     */
    public void setSpellFlavourTextPlain(@Nullable String spellFlavourTextPlain) {
        mSpellFlavourTextPlain = spellFlavourTextPlain;
    }

    /**
     * Get the {@code spell_flavour_text_formatted} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpellFlavourTextFormatted() {
        return mSpellFlavourTextFormatted;
    }

    /**
     * Set the {@code spell_flavour_text_formatted} value.
     * Can be {@code null}.
     */
    public void setSpellFlavourTextFormatted(@Nullable String spellFlavourTextFormatted) {
        mSpellFlavourTextFormatted = spellFlavourTextFormatted;
    }

    /**
     * Get the {@code spell_is_ritual} value.
     */
    public boolean getSpellIsRitual() {
        return mSpellIsRitual;
    }

    /**
     * Set the {@code spell_is_ritual} value.
     */
    public void setSpellIsRitual(boolean spellIsRitual) {
        mSpellIsRitual = spellIsRitual;
    }

    /**
     * Get the {@code character_class__id} value.
     */
    public long getCharacterClassId() {
        return mCharacterClassId;
    }

    /**
     * Set the {@code character_class__id} value.
     */
    public void setCharacterClassId(long characterClassId) {
        mCharacterClassId = characterClassId;
    }

    /**
     * Get the {@code character_class_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCharacterClassName() {
        return mCharacterClassName;
    }

    /**
     * Set the {@code character_class_name} value.
     * Must not be {@code null}.
     */
    public void setCharacterClassName(@NonNull String characterClassName) {
        if (characterClassName == null) throw new IllegalArgumentException("characterClassName must not be null");
        mCharacterClassName = characterClassName;
    }

    /**
     * Get the {@code character_class_level} value.
     */
    public int getCharacterClassLevel() {
        return mCharacterClassLevel;
    }

    /**
     * Set the {@code character_class_level} value.
     */
    public void setCharacterClassLevel(int characterClassLevel) {
        mCharacterClassLevel = characterClassLevel;
    }

    /**
     * Get the {@code character_class_extra} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCharacterClassExtra() {
        return mCharacterClassExtra;
    }

    /**
     * Set the {@code character_class_extra} value.
     * Can be {@code null}.
     */
    public void setCharacterClassExtra(@Nullable String characterClassExtra) {
        mCharacterClassExtra = characterClassExtra;
    }

    /**
     * Get the {@code component__id} value.
     */
    public long getComponentId() {
        return mComponentId;
    }

    /**
     * Set the {@code component__id} value.
     */
    public void setComponentId(long componentId) {
        mComponentId = componentId;
    }

    /**
     * Get the {@code component_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getComponentName() {
        return mComponentName;
    }

    /**
     * Set the {@code component_name} value.
     * Must not be {@code null}.
     */
    public void setComponentName(@NonNull String componentName) {
        if (componentName == null) throw new IllegalArgumentException("componentName must not be null");
        mComponentName = componentName;
    }

    /**
     * Get the {@code descriptor__id} value.
     */
    public long getDescriptorId() {
        return mDescriptorId;
    }

    /**
     * Set the {@code descriptor__id} value.
     */
    public void setDescriptorId(long descriptorId) {
        mDescriptorId = descriptorId;
    }

    /**
     * Get the {@code descriptor_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDescriptorName() {
        return mDescriptorName;
    }

    /**
     * Set the {@code descriptor_name} value.
     * Must not be {@code null}.
     */
    public void setDescriptorName(@NonNull String descriptorName) {
        if (descriptorName == null) throw new IllegalArgumentException("descriptorName must not be null");
        mDescriptorName = descriptorName;
    }

    /**
     * Get the {@code school__id} value.
     */
    public long getSchoolId() {
        return mSchoolId;
    }

    /**
     * Set the {@code school__id} value.
     */
    public void setSchoolId(long schoolId) {
        mSchoolId = schoolId;
    }

    /**
     * Get the {@code school_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSchoolName() {
        return mSchoolName;
    }

    /**
     * Set the {@code school_name} value.
     * Must not be {@code null}.
     */
    public void setSchoolName(@NonNull String schoolName) {
        if (schoolName == null) throw new IllegalArgumentException("schoolName must not be null");
        mSchoolName = schoolName;
    }

    /**
     * Get the {@code subschool__id} value.
     */
    public long getSubschoolId() {
        return mSubschoolId;
    }

    /**
     * Set the {@code subschool__id} value.
     */
    public void setSubschoolId(long subschoolId) {
        mSubschoolId = subschoolId;
    }

    /**
     * Get the {@code subschool_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSubschoolName() {
        return mSubschoolName;
    }

    /**
     * Set the {@code subschool_name} value.
     * Must not be {@code null}.
     */
    public void setSubschoolName(@NonNull String subschoolName) {
        if (subschoolName == null) throw new IllegalArgumentException("subschoolName must not be null");
        mSubschoolName = subschoolName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpellBean bean = (SpellBean) o;
        return mId == bean.mId;
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    /**
     * Instantiate a new SpellCompositeBean with specified values.
     */
    @NonNull
    public static SpellBean newInstance(long id, long spellId, @NonNull String spellName, long rulebookId, @NonNull String rulebookName, int spellPage, @NonNull String spellCastingTime, @NonNull String spellRange, @NonNull String spellTarget, @NonNull String spellEffect, @Nullable String spellArea, @NonNull String spellDuration, @Nullable String spellSavingThrow, @Nullable String spellSpellResistance, @Nullable String spellDescriptionPlain, @Nullable String spellDescriptionFormatted, @Nullable String spellShortDescriptionPlain, @Nullable String spellShortDescriptionFormatted, @Nullable String spellFlavourTextPlain, @Nullable String spellFlavourTextFormatted, boolean spellIsRitual, long characterClassId, @NonNull String characterClassName, int characterClassLevel, @Nullable String characterClassExtra, long componentId, @NonNull String componentName, long descriptorId, @NonNull String descriptorName, long schoolId, @NonNull String schoolName, long subschoolId, @NonNull String subschoolName) {
        if (spellName == null) throw new IllegalArgumentException("spellName must not be null");
        if (rulebookName == null) throw new IllegalArgumentException("rulebookName must not be null");
        if (spellCastingTime == null) throw new IllegalArgumentException("spellCastingTime must not be null");
        if (spellRange == null) throw new IllegalArgumentException("spellRange must not be null");
        if (spellTarget == null) throw new IllegalArgumentException("spellTarget must not be null");
        if (spellEffect == null) throw new IllegalArgumentException("spellEffect must not be null");
        if (spellDuration == null) throw new IllegalArgumentException("spellDuration must not be null");
        if (characterClassName == null) throw new IllegalArgumentException("characterClassName must not be null");
        if (componentName == null) throw new IllegalArgumentException("componentName must not be null");
        if (descriptorName == null) throw new IllegalArgumentException("descriptorName must not be null");
        if (schoolName == null) throw new IllegalArgumentException("schoolName must not be null");
        if (subschoolName == null) throw new IllegalArgumentException("subschoolName must not be null");
        SpellBean res = new SpellBean();
        res.mId = id;
        res.mSpellId = spellId;
        res.mSpellName = spellName;
        res.mRulebookId = rulebookId;
        res.mRulebookName = rulebookName;
        res.mSpellPage = spellPage;
        res.mSpellCastingTime = spellCastingTime;
        res.mSpellRange = spellRange;
        res.mSpellTarget = spellTarget;
        res.mSpellEffect = spellEffect;
        res.mSpellArea = spellArea;
        res.mSpellDuration = spellDuration;
        res.mSpellSavingThrow = spellSavingThrow;
        res.mSpellSpellResistance = spellSpellResistance;
        res.mSpellDescriptionPlain = spellDescriptionPlain;
        res.mSpellDescriptionFormatted = spellDescriptionFormatted;
        res.mSpellShortDescriptionPlain = spellShortDescriptionPlain;
        res.mSpellShortDescriptionFormatted = spellShortDescriptionFormatted;
        res.mSpellFlavourTextPlain = spellFlavourTextPlain;
        res.mSpellFlavourTextFormatted = spellFlavourTextFormatted;
        res.mSpellIsRitual = spellIsRitual;
        res.mCharacterClassId = characterClassId;
        res.mCharacterClassName = characterClassName;
        res.mCharacterClassLevel = characterClassLevel;
        res.mCharacterClassExtra = characterClassExtra;
        res.mComponentId = componentId;
        res.mComponentName = componentName;
        res.mDescriptorId = descriptorId;
        res.mDescriptorName = descriptorName;
        res.mSchoolId = schoolId;
        res.mSchoolName = schoolName;
        res.mSubschoolId = subschoolId;
        res.mSubschoolName = subschoolName;
        return res;
    }
    
    /**
     * Instantiate a new SpellCompositeBean with all the values copied from the given model.
     */
    /*
    @NonNull
    public static SpellCompositeBean copy(@NonNull SpellCompositeModel from) {
        SpellCompositeBean res = new SpellCompositeBean();
        res.mId = from.getId();
        res.mSpellId = from.getSpellId();
        res.mSpellName = from.getSpellName();
        res.mRulebookId = from.getRulebookId();
        res.mRulebookName = from.getRulebookName();
        res.mSpellPage = from.getSpellPage();
        res.mSpellCastingTime = from.getSpellCastingTime();
        res.mSpellRange = from.getSpellRange();
        res.mSpellTarget = from.getSpellTarget();
        res.mSpellEffect = from.getSpellEffect();
        res.mSpellArea = from.getSpellArea();
        res.mSpellDuration = from.getSpellDuration();
        res.mSpellSavingThrow = from.getSpellSavingThrow();
        res.mSpellSpellResistance = from.getSpellSpellResistance();
        res.mSpellDescriptionPlain = from.getSpellDescriptionPlain();
        res.mSpellDescriptionFormatted = from.getSpellDescriptionFormatted();
        res.mSpellShortDescriptionPlain = from.getSpellShortDescriptionPlain();
        res.mSpellShortDescriptionFormatted = from.getSpellShortDescriptionFormatted();
        res.mSpellFlavourTextPlain = from.getSpellFlavourTextPlain();
        res.mSpellFlavourTextFormatted = from.getSpellFlavourTextFormatted();
        res.mSpellIsRitual = from.getSpellIsRitual();
        res.mCharacterClassId = from.getCharacterClassId();
        res.mCharacterClassName = from.getCharacterClassName();
        res.mCharacterClassLevel = from.getCharacterClassLevel();
        res.mCharacterClassExtra = from.getCharacterClassExtra();
        res.mComponentId = from.getComponentId();
        res.mComponentName = from.getComponentName();
        res.mDescriptorId = from.getDescriptorId();
        res.mDescriptorName = from.getDescriptorName();
        res.mSchoolId = from.getSchoolId();
        res.mSchoolName = from.getSchoolName();
        res.mSubschoolId = from.getSubschoolId();
        res.mSubschoolName = from.getSubschoolName();
        return res;
    }
    */

    public static class Builder {
        private SpellBean mRes = new SpellBean();

        /**
         * Primary key.
         */
        public Builder id(long id) {
            mRes.mId = id;
            return this;
        }

        /**
         * Set the {@code spell__id} value.
         */
        public Builder spellId(long spellId) {
            mRes.mSpellId = spellId;
            return this;
        }

        /**
         * Set the {@code spell_name} value.
         * Must not be {@code null}.
         */
        public Builder spellName(@NonNull String spellName) {
            if (spellName == null) throw new IllegalArgumentException("spellName must not be null");
            mRes.mSpellName = spellName;
            return this;
        }

        /**
         * Set the {@code rulebook__id} value.
         */
        public Builder rulebookId(long rulebookId) {
            mRes.mRulebookId = rulebookId;
            return this;
        }

        /**
         * Set the {@code rulebook_name} value.
         * Must not be {@code null}.
         */
        public Builder rulebookName(@NonNull String rulebookName) {
            if (rulebookName == null) throw new IllegalArgumentException("rulebookName must not be null");
            mRes.mRulebookName = rulebookName;
            return this;
        }

        /**
         * Set the {@code spell_page} value.
         */
        public Builder spellPage(int spellPage) {
            mRes.mSpellPage = spellPage;
            return this;
        }

        /**
         * Set the {@code spell_casting_time} value.
         * Must not be {@code null}.
         */
        public Builder spellCastingTime(@NonNull String spellCastingTime) {
            if (spellCastingTime == null) throw new IllegalArgumentException("spellCastingTime must not be null");
            mRes.mSpellCastingTime = spellCastingTime;
            return this;
        }

        /**
         * Set the {@code spell_range} value.
         * Must not be {@code null}.
         */
        public Builder spellRange(@NonNull String spellRange) {
            if (spellRange == null) throw new IllegalArgumentException("spellRange must not be null");
            mRes.mSpellRange = spellRange;
            return this;
        }

        /**
         * Set the {@code spell_target} value.
         * Must not be {@code null}.
         */
        public Builder spellTarget(@NonNull String spellTarget) {
            if (spellTarget == null) throw new IllegalArgumentException("spellTarget must not be null");
            mRes.mSpellTarget = spellTarget;
            return this;
        }

        /**
         * Set the {@code spell_effect} value.
         * Must not be {@code null}.
         */
        public Builder spellEffect(@NonNull String spellEffect) {
            if (spellEffect == null) throw new IllegalArgumentException("spellEffect must not be null");
            mRes.mSpellEffect = spellEffect;
            return this;
        }

        /**
         * Set the {@code spell_area} value.
         * Can be {@code null}.
         */
        public Builder spellArea(@Nullable String spellArea) {
            mRes.mSpellArea = spellArea;
            return this;
        }

        /**
         * Set the {@code spell_duration} value.
         * Must not be {@code null}.
         */
        public Builder spellDuration(@NonNull String spellDuration) {
            if (spellDuration == null) throw new IllegalArgumentException("spellDuration must not be null");
            mRes.mSpellDuration = spellDuration;
            return this;
        }

        /**
         * Set the {@code spell_saving_throw} value.
         * Can be {@code null}.
         */
        public Builder spellSavingThrow(@Nullable String spellSavingThrow) {
            mRes.mSpellSavingThrow = spellSavingThrow;
            return this;
        }

        /**
         * Set the {@code spell_spell_resistance} value.
         * Can be {@code null}.
         */
        public Builder spellSpellResistance(@Nullable String spellSpellResistance) {
            mRes.mSpellSpellResistance = spellSpellResistance;
            return this;
        }

        /**
         * Set the {@code spell_description_plain} value.
         * Can be {@code null}.
         */
        public Builder spellDescriptionPlain(@Nullable String spellDescriptionPlain) {
            mRes.mSpellDescriptionPlain = spellDescriptionPlain;
            return this;
        }

        /**
         * Set the {@code spell_description_formatted} value.
         * Can be {@code null}.
         */
        public Builder spellDescriptionFormatted(@Nullable String spellDescriptionFormatted) {
            mRes.mSpellDescriptionFormatted = spellDescriptionFormatted;
            return this;
        }

        /**
         * Set the {@code spell_short_description_plain} value.
         * Can be {@code null}.
         */
        public Builder spellShortDescriptionPlain(@Nullable String spellShortDescriptionPlain) {
            mRes.mSpellShortDescriptionPlain = spellShortDescriptionPlain;
            return this;
        }

        /**
         * Set the {@code spell_short_description_formatted} value.
         * Can be {@code null}.
         */
        public Builder spellShortDescriptionFormatted(@Nullable String spellShortDescriptionFormatted) {
            mRes.mSpellShortDescriptionFormatted = spellShortDescriptionFormatted;
            return this;
        }

        /**
         * Set the {@code spell_flavour_text_plain} value.
         * Can be {@code null}.
         */
        public Builder spellFlavourTextPlain(@Nullable String spellFlavourTextPlain) {
            mRes.mSpellFlavourTextPlain = spellFlavourTextPlain;
            return this;
        }

        /**
         * Set the {@code spell_flavour_text_formatted} value.
         * Can be {@code null}.
         */
        public Builder spellFlavourTextFormatted(@Nullable String spellFlavourTextFormatted) {
            mRes.mSpellFlavourTextFormatted = spellFlavourTextFormatted;
            return this;
        }

        /**
         * Set the {@code spell_is_ritual} value.
         */
        public Builder spellIsRitual(boolean spellIsRitual) {
            mRes.mSpellIsRitual = spellIsRitual;
            return this;
        }

        /**
         * Set the {@code character_class__id} value.
         */
        public Builder characterClassId(long characterClassId) {
            mRes.mCharacterClassId = characterClassId;
            return this;
        }

        /**
         * Set the {@code character_class_name} value.
         * Must not be {@code null}.
         */
        public Builder characterClassName(@NonNull String characterClassName) {
            if (characterClassName == null) throw new IllegalArgumentException("characterClassName must not be null");
            mRes.mCharacterClassName = characterClassName;
            return this;
        }

        /**
         * Set the {@code character_class_level} value.
         */
        public Builder characterClassLevel(int characterClassLevel) {
            mRes.mCharacterClassLevel = characterClassLevel;
            return this;
        }

        /**
         * Set the {@code character_class_extra} value.
         * Can be {@code null}.
         */
        public Builder characterClassExtra(@Nullable String characterClassExtra) {
            mRes.mCharacterClassExtra = characterClassExtra;
            return this;
        }

        /**
         * Set the {@code component__id} value.
         */
        public Builder componentId(long componentId) {
            mRes.mComponentId = componentId;
            return this;
        }

        /**
         * Set the {@code component_name} value.
         * Must not be {@code null}.
         */
        public Builder componentName(@NonNull String componentName) {
            if (componentName == null) throw new IllegalArgumentException("componentName must not be null");
            mRes.mComponentName = componentName;
            return this;
        }

        /**
         * Set the {@code descriptor__id} value.
         */
        public Builder descriptorId(long descriptorId) {
            mRes.mDescriptorId = descriptorId;
            return this;
        }

        /**
         * Set the {@code descriptor_name} value.
         * Must not be {@code null}.
         */
        public Builder descriptorName(@NonNull String descriptorName) {
            if (descriptorName == null) throw new IllegalArgumentException("descriptorName must not be null");
            mRes.mDescriptorName = descriptorName;
            return this;
        }

        /**
         * Set the {@code school__id} value.
         */
        public Builder schoolId(long schoolId) {
            mRes.mSchoolId = schoolId;
            return this;
        }

        /**
         * Set the {@code school_name} value.
         * Must not be {@code null}.
         */
        public Builder schoolName(@NonNull String schoolName) {
            if (schoolName == null) throw new IllegalArgumentException("schoolName must not be null");
            mRes.mSchoolName = schoolName;
            return this;
        }

        /**
         * Set the {@code subschool__id} value.
         */
        public Builder subschoolId(long subschoolId) {
            mRes.mSubschoolId = subschoolId;
            return this;
        }

        /**
         * Set the {@code subschool_name} value.
         * Must not be {@code null}.
         */
        public Builder subschoolName(@NonNull String subschoolName) {
            if (subschoolName == null) throw new IllegalArgumentException("subschoolName must not be null");
            mRes.mSubschoolName = subschoolName;
            return this;
        }

        /**
         * Get a new SpellBean built with the given values.
         */
        public SpellBean build() {
            if (mRes.mSpellName == null) throw new IllegalArgumentException("spellName must not be null");
            if (mRes.mRulebookName == null) throw new IllegalArgumentException("rulebookName must not be null");
            if (mRes.mSpellCastingTime == null) throw new IllegalArgumentException("spellCastingTime must not be null");
            if (mRes.mSpellRange == null) throw new IllegalArgumentException("spellRange must not be null");
            if (mRes.mSpellTarget == null) throw new IllegalArgumentException("spellTarget must not be null");
            if (mRes.mSpellEffect == null) throw new IllegalArgumentException("spellEffect must not be null");
            if (mRes.mSpellDuration == null) throw new IllegalArgumentException("spellDuration must not be null");
            if (mRes.mCharacterClassName == null) throw new IllegalArgumentException("characterClassName must not be null");
            if (mRes.mComponentName == null) throw new IllegalArgumentException("componentName must not be null");
            if (mRes.mDescriptorName == null) throw new IllegalArgumentException("descriptorName must not be null");
            if (mRes.mSchoolName == null) throw new IllegalArgumentException("schoolName must not be null");
            if (mRes.mSubschoolName == null) throw new IllegalArgumentException("subschoolName must not be null");
            return mRes;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
