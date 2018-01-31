package pl.dzielins42.spellcontentprovider.school;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;

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
    protected void modifyBean(SchoolBean schoolBean) {
        schoolBean.setName(schoolBean.getName() + "_changed");
    }

    @Override
    protected SchoolDao dao() {
        return new SchoolDao(RuntimeEnvironment.application);
    }

}