package pl.dzielins42.spellcontentprovider.rulebook;

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
public class RulebookDaoTest extends AbsDaoTest {

    private RulebookDao mDao;

    @Before
    public void setup() throws Exception {
        super.setUp();
        mDao = new RulebookDao(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        RulebookBean bean = new RulebookBean();
        bean.setName("Test rulebook");

        mDao.save(bean);

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                1,
                db.query(
                        RulebookColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
        db.close();
    }

}