package pl.dzielins42.spellcontentprovider.component;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@Data
public class ComponentWithExtraBean extends ComponentBean {

    private String mExtra;

    /**
     * Instantiate a new ComponentWithExtraBean with specified values.
     */
    @lombok.Builder
    @NonNull
    public static ComponentWithExtraBean newInstance(
            long id,
            @NonNull String name,
            @Nullable String extra
    ) {
        if (name == null) throw new IllegalArgumentException("name must not be null");
        ComponentWithExtraBean res = new ComponentWithExtraBean();
        res.setId(id);
        res.setName(name);
        res.mExtra = extra;
        return res;
    }

}
