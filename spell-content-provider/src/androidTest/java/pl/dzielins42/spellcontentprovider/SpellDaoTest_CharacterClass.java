package pl.dzielins42.spellcontentprovider;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;

@RunWith(AndroidJUnit4.class)
public class SpellDaoTest_CharacterClass extends SpellDaoTest {

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
        CharacterClassBean bean = new CharacterClassBean();
        bean.setName("Test class");

        SpellDao.save(mMockResolver, bean);

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
        SpellDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                CharacterClassColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test class A", cursor.getString(cursor.getColumnIndexOrThrow(CharacterClassColumns.NAME)));

        id = bean.getId();

        bean.setName("Test class B");
        SpellDao.save(mMockResolver, bean);

        cursor = getReadableDatabase().query(
                CharacterClassColumns.TABLE_NAME, null, null, null, null, null, null
        );
        cursor.moveToFirst();
        assertEquals("Test class B", cursor.getString(cursor.getColumnIndexOrThrow(CharacterClassColumns.NAME)));
        assertEquals(id, cursor.getLong(cursor.getColumnIndexOrThrow(CharacterClassColumns._ID)));
    }

}