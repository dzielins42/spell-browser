package pl.dzielins42.spellcontentprovider.descriptor;

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
public class DescriptorDaoTest extends AbsDaoTest {

    private DescriptorDao mDao;

    @Before
    public void setup() throws Exception {
        super.setUp();
        mDao = new DescriptorDao(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        DescriptorBean bean = new DescriptorBean();
        bean.setName("Test descriptor");

        mDao.save(bean);

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                1,
                db.query(
                        DescriptorColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
        db.close();
    }

}