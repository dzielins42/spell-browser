package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;

@RunWith(AndroidJUnit4.class)
public class CharacterClassDaoTest extends DaoTest {

    private CharacterClassDao mDao;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mDao = new CharacterClassDao(getMockContext());
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void save_insert() throws Exception {
        CharacterClassBean bean = new CharacterClassBean();
        bean.setName("Test class");

        mDao.save(bean);

        assertEquals(
                1,
                getReadableDatabase().query(
                        CharacterClassColumns.TABLE_NAME, null, null, null, null, null, null
                ).getCount()
        );
    }

    @Test
    public void save_update() throws Exception {
        Cursor cursor;
        long id;
        CharacterClassBean bean = new CharacterClassBean();

        bean.setName("Test class A");
        mDao.save(bean);

        cursor = getReadableDatabase().query(
                CharacterClassColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test class A", cursor.getString(cursor.getColumnIndexOrThrow(CharacterClassColumns.NAME)));
        cursor.close();

        id = bean.getId();

        bean.setName("Test class B");
        mDao.save(bean);

        cursor = getReadableDatabase().query(
                CharacterClassColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test class B", cursor.getString(cursor.getColumnIndexOrThrow(CharacterClassColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(CharacterClassColumns._ID)));
        cursor.close();
    }

}