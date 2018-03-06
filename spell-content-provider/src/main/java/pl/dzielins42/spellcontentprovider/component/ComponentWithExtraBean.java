package pl.dzielins42.spellcontentprovider.component;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@Data
@Builder
public class ComponentWithExtraBean {

    private long mId;
    private String mName;
    private String mExtra;

}
