package test.psidemo.model;

import java.util.List;
import java.util.Map;

/**
 * Holds the Psi data.
 */

public class PsiData {
    private Map<String, RegionMetaData> mRegionMetadata;
    private List<PsiDataItem> mDataItems;

    public void setDataItems(List<PsiDataItem> dataItems) {
        mDataItems = dataItems;
    }

    public List<PsiDataItem> getDataItems() {
        return  mDataItems;
    }

    public void setRegionMetadata(Map<String, RegionMetaData> metadata) {
        mRegionMetadata = metadata;
    }

    public Map<String, RegionMetaData> getRegionMetadata() {
        return  mRegionMetadata;
    }
}
