package pl.dzielins42.spellcontentprovider.component;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import pl.dzielins42.spellcontentprovider.AbsDaoTest;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class ComponentDaoTest extends AbsDaoTest {

    private ComponentDao mDao;

    @Before
    public void setup() throws Exception {
        super.setUp();
        mDao = new ComponentDao(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        ComponentBean bean = new ComponentBean();
        bean.setName("Test component");

        mDao.save(bean);

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                1,
                db.query(
                        ComponentColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
        db.close();
    }

}