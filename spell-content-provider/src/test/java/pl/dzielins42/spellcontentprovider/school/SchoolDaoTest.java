package pl.dzielins42.spellcontentprovider.school;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SchoolDaoTest
        extends SimpleDaoTest<SchoolBean, SchoolSelection, SchoolDao, SchoolColumns> {

    @Override
    protected SchoolBean[] testBeans() {
        return new SchoolBean[]{
                SchoolBean.newBuilder().name("Test1").build(),
                SchoolBean.newBuilder().name("Test2").build(),
                SchoolBean.newBuilder().name("Test3").build(),
                SchoolBean.newBuilder().name("Test4").build(),
                SchoolBean.newBuilder().name("Test5").build(),
        };
    }

    @Override
    protected SchoolSelection selectionForSingleTestBean() {
        return new SchoolSelection().name("Test1");
    }

    @Override
    protected SchoolSelection selectionForThreeTestBeans() {
        return new SchoolSelection().name("Test1", "Test2", "Test3");
    }

    @Override
    protected String tableName() {
        return SchoolColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(SchoolBean SchoolBean) {
        SchoolBean.setName(SchoolBean.getName() + "_changed");
    }

    @Override
    protected SchoolDao dao() {
        return new SchoolDao(RuntimeEnvironment.application);
    }

    @Test
    public void remove_cascade() throws Exception {
        SchoolBean parent = SchoolBean.newInstance(0,"Parent",null);
        long parentId = mDao.save(parent).blockingGet();
        SchoolBean child = SchoolBean.newInstance(0,"Child",parentId);
        long childId = mDao.save(child).blockingGet();

        SchoolSelection byParentIdSelection = new SchoolSelection().parentId(parentId);
        List<SchoolBean> queryResult;
        queryResult = mDao.get(byParentIdSelection).blockingFirst();

        assertTrue(!queryResult.isEmpty());
        assertEquals(queryResult.size(),1);
        assertEquals(queryResult.get(0).getId(), childId);

        mDao.remove(parent).blockingGet();
        queryResult = mDao.get(byParentIdSelection).blockingFirst();
        assertTrue(queryResult.isEmpty());
    }

}