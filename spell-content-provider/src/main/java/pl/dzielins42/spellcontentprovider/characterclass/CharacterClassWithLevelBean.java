package pl.dzielins42.spellcontentprovider.characterclass;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@Data
public class CharacterClassWithLevelBean extends CharacterClassBean {

    private int mLevel;
    private String mExtra;

    /**
     * Instantiate a new CharacterClassWithLevelBean with specified values.
     */
    @lombok.Builder
    @NonNull
    public static CharacterClassWithLevelBean newInstance(
            long id,
            @NonNull String name,
            int level,
            @Nullable String extra
    ) {
        if (name == null) throw new IllegalArgumentException("name must not be null");
        CharacterClassWithLevelBean res = new CharacterClassWithLevelBean();
        res.setId(id);
        res.setName(name);
        res.mLevel = level;
        res.mExtra = extra;
        return res;
    }

}
