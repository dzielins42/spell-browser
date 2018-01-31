package pl.dzielins42.spellcontentprovider.descriptor;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;

@RunWith(RobolectricTestRunner.class)
public class DescriptorDaoTest
        extends SimpleDaoTest<DescriptorBean, DescriptorSelection, DescriptorDao, DescriptorColumns> {

    @Override
    protected DescriptorBean[] testBeans() {
        return new DescriptorBean[]{
                DescriptorBean.newBuilder().name("Test1").build(),
                DescriptorBean.newBuilder().name("Test2").build(),
                DescriptorBean.newBuilder().name("Test3").build(),
                DescriptorBean.newBuilder().name("Test4").build(),
                DescriptorBean.newBuilder().name("Test5").build(),
        };
    }

    @Override
    protected DescriptorSelection selectionForSingleTestBean() {
        return new DescriptorSelection().name("Test1");
    }

    @Override
    protected DescriptorSelection selectionForThreeTestBeans() {
        return new DescriptorSelection().name("Test1", "Test2", "Test3");
    }

    @Override
    protected String tableName() {
        return DescriptorColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(DescriptorBean descriptorBean) {
        descriptorBean.setName(descriptorBean.getName() + "_changed");
    }

    @Override
    protected DescriptorDao dao() {
        return new DescriptorDao(RuntimeEnvironment.application);
    }

}