package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.Set;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassContentValues;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassSelection;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentContentValues;
import pl.dzielins42.spellcontentprovider.component.ComponentSelection;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorContentValues;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorSelection;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookContentValues;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookSelection;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolContentValues;
import pl.dzielins42.spellcontentprovider.school.SchoolSelection;
import pl.dzielins42.spellcontentprovider.spell.SpellBean;
import pl.dzielins42.spellcontentprovider.spell.SpellContentValues;
import pl.dzielins42.spellcontentprovider.spell.SpellSelection;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesBean;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesContentValues;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesSelection;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsBean;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsContentValues;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsSelection;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsBean;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsContentValues;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsSelection;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsBean;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsContentValues;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsSelection;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolContentValues;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolSelection;

public class SpellDao {

    public static void save(
            @NonNull Context context,
            @NonNull CharacterClassBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull CharacterClassBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        CharacterClassContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new CharacterClassSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

    public static void save(
            @NonNull Context context,
            @NonNull ComponentBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull ComponentBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        ComponentContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new ComponentSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

    public static void save(
            @NonNull Context context,
            @NonNull DescriptorBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull DescriptorBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        DescriptorContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new DescriptorSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

    public static void save(
            @NonNull Context context,
            @NonNull RulebookBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull RulebookBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        RulebookContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new RulebookSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

    public static void save(
            @NonNull Context context,
            @NonNull SchoolBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SchoolBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        SchoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new SchoolSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

    public static void save(
            @NonNull Context context,
            @NonNull SpellBean spellBean,
            @NonNull Set<SpellsToCharacterClassesBean> spellsToCharacterClassesBeans,
            @NonNull Set<SpellsToComponentsBean> spellsToComponentsBeans,
            @NonNull Set<SpellsToDescriptorsBean> spellsToDescriptorsBeans,
            @NonNull Set<SpellsToSchoolsBean> spellsToSchoolsBeans
    ) {
        save(
                context.getContentResolver(),
                spellBean,
                spellsToCharacterClassesBeans,
                spellsToComponentsBeans,
                spellsToDescriptorsBeans,
                spellsToSchoolsBeans
        );
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellBean spellBean,
            @NonNull Set<SpellsToCharacterClassesBean> spellsToCharacterClassesBeans,
            @NonNull Set<SpellsToComponentsBean> spellsToComponentsBeans,
            @NonNull Set<SpellsToDescriptorsBean> spellsToDescriptorsBeans,
            @NonNull Set<SpellsToSchoolsBean> spellsToSchoolsBeans
    ) {
        final long spellId = spellBean.getId();
        final boolean isUpdate = spellId > 0;

        SpellContentValues spellContentValues = ContentValuesUtils.beanToContentValues(spellBean);

        if (isUpdate) {
            // Remove records from bridge tables for given spell
            new SpellsToCharacterClassesSelection().spellId(spellId).delete(contentResolver);
            new SpellsToComponentsSelection().spellId(spellId).delete(contentResolver);
            new SpellsToDescriptorsSelection().spellId(spellId).delete(contentResolver);
            new SpellsToSchoolsSelection().frSpellId(spellId).delete(contentResolver);
        }

        if (isUpdate) {
            spellContentValues.update(contentResolver, new SpellSelection().id(spellBean.getId()));
        } else {
            spellContentValues.insert(contentResolver);
        }

        if (spellsToCharacterClassesBeans != null && !spellsToCharacterClassesBeans.isEmpty()) {
            for (SpellsToCharacterClassesBean bean : spellsToCharacterClassesBeans) {
                save(contentResolver, bean);
            }
        }
        if (spellsToComponentsBeans != null && !spellsToComponentsBeans.isEmpty()) {
            for (SpellsToComponentsBean bean : spellsToComponentsBeans) {
                save(contentResolver, bean);
            }
        }
        if (spellsToDescriptorsBeans != null && !spellsToDescriptorsBeans.isEmpty()) {
            for (SpellsToDescriptorsBean bean : spellsToDescriptorsBeans) {
                save(contentResolver, bean);
            }
        }
        if (spellsToSchoolsBeans != null && !spellsToSchoolsBeans.isEmpty()) {
            for (SpellsToSchoolsBean bean : spellsToSchoolsBeans) {
                save(contentResolver, bean);
            }
        }
    }

    public static void save(
            @NonNull Context context,
            @NonNull SubschoolBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SubschoolBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        SubschoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new SubschoolSelection().id(bean.getId()));
        } else {
            contentValues.insert(contentResolver);
        }
    }

    private static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellsToCharacterClassesBean bean
    ) {
        final SpellsToCharacterClassesContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);
        contentValues.insert(contentResolver);
    }

    private static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellsToComponentsBean bean
    ) {
        final SpellsToComponentsContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);
        contentValues.insert(contentResolver);
    }

    private static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellsToDescriptorsBean bean
    ) {
        final SpellsToDescriptorsContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);
        contentValues.insert(contentResolver);
    }

    private static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellsToSchoolsBean bean
    ) {
        final SpellsToSchoolsContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);
        contentValues.insert(contentResolver);
    }

}
