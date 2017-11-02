package pl.dzielins42.spellcontentprovider.spell.composite;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;

public class SchoolSubschoolBean {

    private SchoolBean mSchool;
    private SubschoolBean mSubschool;

    @NonNull
    public SchoolBean getSchool() {
        return mSchool;
    }

    public void setSchool(@NonNull SchoolBean school) {
        if (school == null) throw new IllegalArgumentException("school must not be null");
        this.mSchool = school;
    }

    @Nullable
    public SubschoolBean getSubschool() {
        return mSubschool;
    }

    public void setSubschool(@Nullable SubschoolBean subschool) {
        this.mSubschool = subschool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolSubschoolBean that = (SchoolSubschoolBean) o;

        if (!mSchool.equals(that.mSchool)) return false;
        return mSubschool != null ? mSubschool.equals(that.mSubschool) : that.mSubschool == null;

    }

    @Override
    public int hashCode() {
        int result = mSchool.hashCode();
        result = 31 * result + (mSubschool != null ? mSubschool.hashCode() : 0);
        return result;
    }

    @NonNull
    public static SchoolSubschoolBean newInstance(@NonNull SchoolBean school, @Nullable SubschoolBean subschool) {
        if (school == null) throw new IllegalArgumentException("school must not be null");
        SchoolSubschoolBean res = new SchoolSubschoolBean();
        res.mSchool = school;
        res.mSubschool = subschool;
        return res;
    }
}
