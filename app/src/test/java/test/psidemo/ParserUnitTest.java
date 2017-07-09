package test.psidemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import test.psidemo.model.PsiData;
import test.psidemo.parser.DataParserImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the parser.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ParserUnitTest {
    @Test
    public void input_null() throws Exception {
        DataParserImpl impl = new DataParserImpl();
        PsiData data = impl.parse(null);
        assertEquals(null, data);
    }

    @Test
    public void input_empty() throws Exception {
        DataParserImpl impl = new DataParserImpl();
        PsiData data = impl.parse("");
        assertEquals(null, data);
    }

    @Test
    public void input_expected() throws Exception {
        DataParserImpl impl = new DataParserImpl();
        PsiData data = impl.parse("{\"region_metadata\":[{\"name\":\"west\",\"label_location\":{\"latitude\":1.35735,\"longitude\":103.7}}],\"items\":[{\"timestamp\":\"2017-07-09T13:00:00+08:00\",\"update_timestamp\":\"2017-07-09T13:06:18+08:00\",\"readings\":{\"psi_twenty_four_hourly\":{\"west\":52,\"national\":54,\"east\":50,\"central\":49,\"south\":54,\"north\":53}}}],\"api_info\":{\"status\":\"healthy\"}}");
        assertNotEquals(null, data);
    }

    @Test
    public void input_missing_field() throws Exception {
        DataParserImpl impl = new DataParserImpl();
        PsiData data = impl.parse("{\"region_metadata\":[{\"name\":\"west\",\"label_location\":{\"latitude\":1.35735}}],\"items\":[{\"timestamp\":\"2017-07-09T13:00:00+08:00\",\"update_timestamp\":\"2017-07-09T13:06:18+08:00\",\"readings\":{\"psi_twenty_four_hourly\":{\"west\":52,\"national\":54,\"east\":50,\"central\":49,\"south\":54,\"north\":53}}}],\"api_info\":{\"status\":\"healthy\"}}");
        assertEquals(null, data);
    }
}