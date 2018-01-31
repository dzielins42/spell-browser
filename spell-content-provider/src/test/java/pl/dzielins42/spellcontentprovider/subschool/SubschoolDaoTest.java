package pl.dzielins42.spellcontentprovider.subschool;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SubschoolDaoTest
        extends SimpleDaoTest<SubschoolBean, SubschoolSelection, SubschoolDao, SubschoolColumns> {

    private SchoolBean[] mSchoolBeans;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Subschool requires school
        mSchoolBeans = new SchoolBean[]{
                SchoolBean.newBuilder().name("Test1").build(),
                SchoolBean.newBuilder().name("Test2").build(),
                SchoolBean.newBuilder().name("Test3").build(),
        };
        SchoolDao schoolDao = new SchoolDao(RuntimeEnvironment.application);
        for (int i = 0; i < mSchoolBeans.length; i++) {
            schoolDao.save(mSchoolBeans[i]).blockingFirst();
        }
    }

    @Override
    protected SubschoolBean[] testBeans() {
        return new SubschoolBean[]{
                SubschoolBean.newBuilder().name("Test1").schoolId(mSchoolBeans[0].getId()).build(),
                SubschoolBean.newBuilder().name("Test2").schoolId(mSchoolBeans[0].getId()).build(),
                SubschoolBean.newBuilder().name("Test3").schoolId(mSchoolBeans[0].getId()).build(),
                SubschoolBean.newBuilder().name("Test4").schoolId(mSchoolBeans[1].getId()).build(),
                SubschoolBean.newBuilder().name("Test5").schoolId(mSchoolBeans[2].getId()).build(),
        };
    }

    @Override
    protected SubschoolSelection selectionForSingleTestBean() {
        return new SubschoolSelection().name("Test1");
    }

    @Override
    protected SubschoolSelection selectionForThreeTestBeans() {
        return new SubschoolSelection().schoolId(mSchoolBeans[0].getId());
    }

    @Override
    protected String tableName() {
        return SubschoolColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(SubschoolBean subschoolBean) {
        subschoolBean.setName(subschoolBean.getName() + "_changed");
    }

    @Override
    protected SubschoolDao dao() {
        return new SubschoolDao(RuntimeEnvironment.application);
    }

    @Override
    protected void clearAllData(SQLiteDatabase db) {
        super.clearAllData(db);
        db.execSQL("DELETE FROM " + SchoolColumns.TABLE_NAME + ";");
    }

    @Test
    public void remove_cascade() throws Exception {
        insertTestBeans();
        SchoolDao schoolDao = new SchoolDao(RuntimeEnvironment.application);
        schoolDao.remove(mSchoolBeans[0]).blockingFirst();
        SubschoolSelection selection = selectionForThreeTestBeans();
        List<SubschoolBean> querryResult = mDao.get(selection).blockingFirst();
        assertEquals(true, (querryResult == null | querryResult.isEmpty()));
    }

}