package pl.dzielins42.spellcontentprovider.spell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface SpellModel {
    /**
     * Primary key.
     */
    long getId();

    /**
     * Get the {@code spell__id} value.
     */
    long getSpellId();

    /**
     * Get the {@code spell_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSpellName();

    /**
     * Get the {@code rulebook__id} value.
     */
    long getRulebookId();

    /**
     * Get the {@code rulebook_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getRulebookName();

    /**
     * Get the {@code spell_page} value.
     */
    int getSpellPage();

    /**
     * Get the {@code spell_casting_time} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSpellCastingTime();

    /**
     * Get the {@code spell_range} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSpellRange();

    /**
     * Get the {@code spell_target} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSpellTarget();

    /**
     * Get the {@code spell_effect} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSpellEffect();

    /**
     * Get the {@code spell_area} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellArea();

    /**
     * Get the {@code spell_duration} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSpellDuration();

    /**
     * Get the {@code spell_saving_throw} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellSavingThrow();

    /**
     * Get the {@code spell_spell_resistance} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellSpellResistance();

    /**
     * Get the {@code spell_description_plain} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellDescriptionPlain();

    /**
     * Get the {@code spell_description_formatted} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellDescriptionFormatted();

    /**
     * Get the {@code spell_short_description_plain} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellShortDescriptionPlain();

    /**
     * Get the {@code spell_short_description_formatted} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellShortDescriptionFormatted();

    /**
     * Get the {@code spell_flavour_text_plain} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellFlavourTextPlain();

    /**
     * Get the {@code spell_flavour_text_formatted} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpellFlavourTextFormatted();

    /**
     * Get the {@code spell_is_ritual} value.
     */
    boolean getSpellIsRitual();

    /**
     * Get the {@code character_class__id} value.
     */
    long getCharacterClassId();

    /**
     * Get the {@code character_class_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getCharacterClassName();

    /**
     * Get the {@code character_class_level} value.
     */
    int getCharacterClassLevel();

    /**
     * Get the {@code character_class_extra} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCharacterClassExtra();

    /**
     * Get the {@code component__id} value.
     */
    long getComponentId();

    /**
     * Get the {@code component_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getComponentName();

    /**
     * Get the {@code descriptor__id} value.
     */
    long getDescriptorId();

    /**
     * Get the {@code descriptor_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDescriptorName();

    /**
     * Get the {@code school__id} value.
     */
    long getSchoolId();

    /**
     * Get the {@code school_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSchoolName();

    /**
     * Get the {@code subschool__id} value.
     */
    long getSubschoolId();

    /**
     * Get the {@code subschool_name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSubschoolName();
}
