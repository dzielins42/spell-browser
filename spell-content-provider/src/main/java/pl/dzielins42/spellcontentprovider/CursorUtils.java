package pl.dzielins42.spellcontentprovider;

import android.support.annotation.NonNull;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassCursor;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentCursor;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorCursor;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookCursor;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolCursor;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseBean;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseCursor;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolCursor;

public class CursorUtils {

    @NonNull
    static CharacterClassBean cursorToBean(@NonNull CharacterClassCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        CharacterClassBean.Builder builder = CharacterClassBean.newBuilder();
        builder.id(cursor.getId()).name(cursor.getName());

        return builder.build();
    }

    @NonNull
    static ComponentBean cursorToBean(@NonNull ComponentCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        ComponentBean.Builder builder = ComponentBean.newBuilder();
        builder.id(cursor.getId()).name(cursor.getName());

        return builder.build();
    }

    @NonNull
    static DescriptorBean cursorToBean(@NonNull DescriptorCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        DescriptorBean.Builder builder = DescriptorBean.newBuilder();
        builder.id(cursor.getId()).name(cursor.getName());

        return builder.build();
    }

    @NonNull
    static RulebookBean cursorToBean(@NonNull RulebookCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        RulebookBean.Builder builder = RulebookBean.newBuilder();
        builder.id(cursor.getId()).name(cursor.getName());

        return builder.build();
    }

    @NonNull
    static SchoolBean cursorToBean(@NonNull SchoolCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        SchoolBean.Builder builder = SchoolBean.newBuilder();
        builder.id(cursor.getId()).name(cursor.getName());

        return builder.build();
    }

    @NonNull
    static SpellBaseBean cursorToBean(@NonNull SpellBaseCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        SpellBaseBean.Builder builder = SpellBaseBean.newBuilder();
        builder.id(cursor.getId()).area(cursor.getArea()).castingTime(cursor.getCastingTime())
                .descriptionFormatted(cursor.getDescriptionFormatted())
                .descriptionPlain(cursor.getDescriptionPlain()).duration(cursor.getDuration())
                .effect(cursor.getEffect()).flavourTextFormatted(cursor.getFlavourTextFormatted())
                .flavourTextPlain(cursor.getFlavourTextPlain()).name(cursor.getName())
                .page(cursor.getPage()).range(cursor.getRange()).rulebookId(cursor.getRulebookId())
                .savingThrow(cursor.getSavingThrow())
                .shortDescriptionFormatted(cursor.getShortDescriptionFormatted())
                .shortDescriptionPlain(cursor.getShortDescriptionPlain())
                .spellResistance(cursor.getSpellResistance()).target(cursor.getTarget());

        return builder.build();
    }

    @NonNull
    static SubschoolBean cursorToBean(@NonNull SubschoolCursor cursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor cannot be null");
        }

        SubschoolBean.Builder builder = SubschoolBean.newBuilder();
        builder.id(cursor.getId()).name(cursor.getName()).schoolId(cursor.getSchoolId());

        return builder.build();
    }

}
