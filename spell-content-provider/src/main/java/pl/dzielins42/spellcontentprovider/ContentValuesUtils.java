package pl.dzielins42.spellcontentprovider;

import android.support.annotation.NonNull;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassContentValues;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentContentValues;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorContentValues;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookContentValues;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolContentValues;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseBean;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseContentValues;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesBean;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesContentValues;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsBean;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsContentValues;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsBean;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsContentValues;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsBean;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsContentValues;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolContentValues;

public class ContentValuesUtils {

    public static CharacterClassContentValues beanToContentValues(@NonNull CharacterClassBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        CharacterClassContentValues contentValues = new CharacterClassContentValues();
        contentValues.putName(bean.getName());

        return contentValues;
    }

    public static ComponentContentValues beanToContentValues(@NonNull ComponentBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        ComponentContentValues contentValues = new ComponentContentValues();
        contentValues.putName(bean.getName());
        contentValues.putExtra(bean.getExtra());

        return contentValues;
    }

    public static DescriptorContentValues beanToContentValues(@NonNull DescriptorBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        DescriptorContentValues contentValues = new DescriptorContentValues();
        contentValues.putName(bean.getName());

        return contentValues;
    }

    public static RulebookContentValues beanToContentValues(@NonNull RulebookBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        RulebookContentValues contentValues = new RulebookContentValues();
        contentValues.putName(bean.getName());

        return contentValues;
    }

    public static SchoolContentValues beanToContentValues(@NonNull SchoolBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SchoolContentValues contentValues = new SchoolContentValues();
        contentValues.putName(bean.getName());

        return contentValues;
    }

    public static SpellBaseContentValues beanToContentValues(@NonNull SpellBaseBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SpellBaseContentValues contentValues = new SpellBaseContentValues();
        contentValues.putArea(bean.getArea());
        contentValues.putCastingTime(bean.getCastingTime());
        contentValues.putDescriptionFormatted(bean.getDescriptionFormatted());
        contentValues.putDescriptionPlain(bean.getDescriptionPlain());
        contentValues.putDuration(bean.getDuration());
        contentValues.putEffect(bean.getEffect());
        contentValues.putFlavourTextFormatted(bean.getFlavourTextFormatted());
        contentValues.putFlavourTextPlain(bean.getFlavourTextPlain());
        contentValues.putIsRitual(bean.getIsRitual());
        contentValues.putName(bean.getName());
        contentValues.putPage(bean.getPage());
        contentValues.putRange(bean.getRange());
        contentValues.putRulebookId(bean.getRulebookId());
        contentValues.putSavingThrow(bean.getSavingThrow());
        contentValues.putShortDescriptionFormatted(bean.getShortDescriptionFormatted());
        contentValues.putShortDescriptionPlain(bean.getShortDescriptionPlain());
        contentValues.putSpellResistance(bean.getSpellResistance());
        contentValues.putTarget(bean.getTarget());

        return contentValues;
    }

    public static SubschoolContentValues beanToContentValues(@NonNull SubschoolBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SubschoolContentValues contentValues = new SubschoolContentValues();
        contentValues.putName(bean.getName());
        contentValues.putSchoolId(bean.getSchoolId());

        return contentValues;
    }

    public static SpellsToCharacterClassesContentValues beanToContentValues(@NonNull SpellsToCharacterClassesBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SpellsToCharacterClassesContentValues contentValues = new SpellsToCharacterClassesContentValues();
        contentValues.putCharacterClassId(bean.getCharacterClassId());
        contentValues.putExtra(bean.getExtra());
        contentValues.putLevel(bean.getLevel());
        contentValues.putSpellId(bean.getSpellId());

        return contentValues;
    }

    public static SpellsToComponentsContentValues beanToContentValues(@NonNull SpellsToComponentsBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SpellsToComponentsContentValues contentValues = new SpellsToComponentsContentValues();
        contentValues.putComponentId(bean.getComponentId());
        contentValues.putSpellId(bean.getSpellId());

        return contentValues;
    }

    public static SpellsToDescriptorsContentValues beanToContentValues(@NonNull SpellsToDescriptorsBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SpellsToDescriptorsContentValues contentValues = new SpellsToDescriptorsContentValues();
        contentValues.putDescriptorId(bean.getDescriptorId());
        contentValues.putSpellId(bean.getSpellId());

        return contentValues;
    }

    public static SpellsToSchoolsContentValues beanToContentValues(@NonNull SpellsToSchoolsBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("bean cannot be null");
        }

        SpellsToSchoolsContentValues contentValues = new SpellsToSchoolsContentValues();
        contentValues.putFrSchoolId(bean.getFrSchoolId());
        contentValues.putFrSpellId(bean.getFrSpellId());
        contentValues.putFrSubschoolId(bean.getFrSubschoolId());

        return contentValues;
    }

}
