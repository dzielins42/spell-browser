package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookColumns;

@RunWith(AndroidJUnit4.class)
public class RulebookDaoTest extends DaoTest {

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
        RulebookBean bean = new RulebookBean();
        bean.setName("Test rulebook");

        RulebookDao.save(mMockResolver, bean);

        assertEquals(
                1,
                getReadableDatabase().query(
                        RulebookColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
    }

    @Test
    public void save_update() throws Exception {
        Cursor cursor;
        long id;
        RulebookBean bean = new RulebookBean();

        bean.setName("Test rulebook A");
        RulebookDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                RulebookColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test rulebook A", cursor.getString(cursor.getColumnIndexOrThrow(RulebookColumns.NAME)));

        id = bean.getId();

        bean.setName("Test rulebook B");
        RulebookDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                RulebookColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test rulebook B", cursor.getString(cursor.getColumnIndexOrThrow(RulebookColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(RulebookColumns._ID)));
    }

}