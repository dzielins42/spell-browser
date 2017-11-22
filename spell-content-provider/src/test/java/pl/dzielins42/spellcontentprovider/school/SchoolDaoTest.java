package pl.dzielins42.spellcontentprovider.school;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


import pl.dzielins42.spellcontentprovider.AbsDaoTest;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class SchoolDaoTest extends AbsDaoTest {

    private SchoolDao mDao;

    @Before
    public void setup() throws Exception {
        super.setUp();
        mDao = new SchoolDao(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        SchoolBean bean = new SchoolBean();
        bean.setName("Test school");

        mDao.save(bean);

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                1,
                db.query(
                        SchoolColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
        db.close();
    }

}