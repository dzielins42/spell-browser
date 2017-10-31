package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorColumns;

@RunWith(AndroidJUnit4.class)
public class DescriptorDaoTest extends DaoTest {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        DescriptorBean bean = new DescriptorBean();
        bean.setName("Test descriptor");

        DescriptorDao.save(mMockResolver, bean);

        assertEquals(
                1,
                getReadableDatabase().query(
                        DescriptorColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
    }

    @Test
    public void save_update() throws Exception {
        Cursor cursor;
        long id;
        DescriptorBean bean = new DescriptorBean();

        bean.setName("Test descriptor A");
        DescriptorDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                DescriptorColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test descriptor A", cursor.getString(cursor.getColumnIndexOrThrow(DescriptorColumns.NAME)));

        id = bean.getId();

        bean.setName("Test descriptor B");
        DescriptorDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                DescriptorColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test descriptor B", cursor.getString(cursor.getColumnIndexOrThrow(DescriptorColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(DescriptorColumns._ID)));
    }

}