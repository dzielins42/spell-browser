package pl.dzielins42.spellcontentprovider.characterclass;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@Data
@Builder
public class CharacterClassWithLevelBean {

    private long mClassId;
    private String mClassName;
    private int mLevel;
    private String mExtra;

}
