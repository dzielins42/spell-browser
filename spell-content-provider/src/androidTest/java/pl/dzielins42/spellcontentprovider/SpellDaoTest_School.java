package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;

@RunWith(AndroidJUnit4.class)
public class SpellDaoTest_School extends SpellDaoTest {

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
        SchoolBean bean = new SchoolBean();
        bean.setName("Test school");

        SpellDao.save(mMockResolver, bean);

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
        SpellDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                SchoolColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test school A", cursor.getString(cursor.getColumnIndexOrThrow(SchoolColumns.NAME)));

        id = bean.getId();

        bean.setName("Test school B");
        SpellDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                SchoolColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test school B", cursor.getString(cursor.getColumnIndexOrThrow(SchoolColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(SchoolColumns._ID)));
    }

}