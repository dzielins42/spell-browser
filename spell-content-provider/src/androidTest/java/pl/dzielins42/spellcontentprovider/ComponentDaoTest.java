package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentColumns;

@RunWith(AndroidJUnit4.class)
public class ComponentDaoTest extends DaoTest {

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
        ComponentBean bean = new ComponentBean();
        bean.setName("Test component");

        ComponentDao.save(mMockResolver, bean);

        assertEquals(
                1,
                getReadableDatabase().query(
                        ComponentColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
    }

    @Test
    public void save_update() throws Exception {
        Cursor cursor;
        long id;
        ComponentBean bean = new ComponentBean();

        bean.setName("Test component A");
        ComponentDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                ComponentColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test component A", cursor.getString(cursor.getColumnIndexOrThrow(ComponentColumns.NAME)));

        id = bean.getId();

        bean.setName("Test component B");
        ComponentDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                ComponentColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test component B", cursor.getString(cursor.getColumnIndexOrThrow(ComponentColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(ComponentColumns._ID)));
    }

}