package test.psidemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import test.psidemo.map.MapData;
import test.psidemo.model.PsiData;
import test.psidemo.util.DataUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the data util.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataUtilUnitTest {
    @Test
    public void input_null() throws Exception {
        DataUtil util = DataUtil.getInstance();
        List<MapData> data = util.getLatestMapData(null);
        assertEquals(null, data);
    }

    @Test
    public void input_null_data_items() throws Exception {
        DataUtil util = DataUtil.getInstance();
        PsiData psiData = new PsiData();
        List<MapData> data = util.getLatestMapData(psiData);
        assertNotEquals(null, data);
        assertEquals(0, data.size());
    }
}