package pl.dzielins42.spellcontentprovider.spell.composite;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;

public class CharacterClassLevelExtraBean {

    private CharacterClassBean mCharacterClass;
    private String mExtra;
    private int mLevel;

    @NonNull
    public CharacterClassBean getCharacterClass() {
        return mCharacterClass;
    }

    public void setCharacterClass(@NonNull CharacterClassBean characterClass) {
        if (characterClass == null) throw new IllegalArgumentException("characterClass must not be null");
        this.mCharacterClass = characterClass;
    }

    @Nullable
    public String getExtra() {
        return mExtra;
    }

    @Nullable
    public void setExtra(String extra) {
        this.mExtra = extra;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        if (level <= 0) throw new IllegalArgumentException("level must not be less or equal 0");
        this.mLevel = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterClassLevelExtraBean that = (CharacterClassLevelExtraBean) o;

        if (mLevel != that.mLevel) return false;
        if (!mCharacterClass.equals(that.mCharacterClass)) return false;
        return mExtra != null ? mExtra.equals(that.mExtra) : that.mExtra == null;

    }

    @Override
    public int hashCode() {
        int result = mCharacterClass.hashCode();
        result = 31 * result + (mExtra != null ? mExtra.hashCode() : 0);
        result = 31 * result + mLevel;
        return result;
    }

    @NonNull
    public static CharacterClassLevelExtraBean newInstance(@NonNull CharacterClassBean characterClass, @Nullable String extra, int level) {
        if (characterClass == null) throw new IllegalArgumentException("characterClass must not be null");
        if (level <= 0) throw new IllegalArgumentException("level must not be less or equal 0");
        CharacterClassLevelExtraBean res = new CharacterClassLevelExtraBean();
        res.mCharacterClass = characterClass;
        res.mExtra = extra;
        res.mLevel = level;
        return res;
    }

}
