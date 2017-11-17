package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;

@RunWith(AndroidJUnit4.class)
public class SchoolDaoTest extends DaoTest {

    private SchoolDao mDao;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mDao = new SchoolDao(getMockContext());
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        SchoolBean bean = new SchoolBean();
        bean.setName("Test school");

        mDao.save(bean);

        assertEquals(
                1,
                getReadableDatabase().query(
                        SchoolColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
    }

    @Test
    public void save_update() throws Exception {
        Cursor cursor;
        long id;
        SchoolBean bean = new SchoolBean();

        bean.setName("Test school A");
        mDao.save(bean);

        cursor = getReadableDatabase().query(
                SchoolColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test school A", cursor.getString(cursor.getColumnIndexOrThrow(SchoolColumns.NAME)));
        cursor.close();

        id = bean.getId();

        bean.setName("Test school B");
        mDao.save(bean);

        cursor = getReadableDatabase().query(
                SchoolColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test school B", cursor.getString(cursor.getColumnIndexOrThrow(SchoolColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(SchoolColumns._ID)));
        cursor.close();
    }

}