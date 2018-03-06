package pl.dzielins42.spellcontentprovider.spell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassWithLevelBean;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentWithExtraBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseModel;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesBean;

@Accessors(prefix = "m")
@Data
@Builder
public class SpellBean {
    private long mId;
    private String mName;
    private long mRulebookId;
    private String mRulebookName;
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

    private List<CharacterClassWithLevelBean> mCharacterClasses;

    private List<ComponentWithExtraBean> mComponents;

    private List<DescriptorBean> mDescriptors;

    private long mSchoolId;
    private long mSchoolMainTypeId;
    private String mSchoolMainTypeName;
    private Long mSubschoolSubTypeId;
    private String mSubschoolSubTypeName;

}
