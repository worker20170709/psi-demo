package test.psidemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import test.psidemo.map.MapData;
import test.psidemo.model.PsiData;
import test.psidemo.model.PsiDataItem;
import test.psidemo.model.RegionMetaData;

/**
 * Utility class for data conversion from "raw" data to data for UI consumers.
 */

public class DataUtil {
    private static DataUtil sInstance;

    public static DataUtil getInstance() {
        if (sInstance == null) {
            sInstance = new DataUtil();
        }

        return  sInstance;
    }

    /*
     * Gets the latest(based on timestamp comparison) data from the supplied PsiData.
     *
     * @param psiData The input PSI data.
     * @return A List of MapData.
     */
    public List<MapData> getLatestMapData(PsiData psiData) {
        List<PsiDataItem> items = psiData.getDataItems();
        PsiDataItem latestItem = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date latestDate = null;

        for (PsiDataItem item : items) {
            try {
                Date date = sdf.parse(item.getTimeStamp());
                if (latestDate == null) {
                    latestDate = date;
                    latestItem = item;
                } else {
                    if (latestDate.before(date)) {
                        latestDate = date;
                        latestItem = item;
                    }
                }
            } catch (ParseException e) {
                // Do nothing.
            }
        }

        ArrayList<MapData> mapDataArr = new ArrayList<>();
        if (latestItem != null) {
            Map<String, Double> psi = latestItem.getPsiTwentyFourHourly();
            Map<String, RegionMetaData> latLng = psiData.getRegionMetadata();

            for (String location : psi.keySet()) {
                if (location.equals("national")) {
                    MapData mapData = new MapData();
                    mapData.setPsi(psi.get(location));
                    mapData.setIsNational(true);
                    mapDataArr.add(mapData);
                } else {
                    RegionMetaData metadata = latLng.get(location);
                    if (metadata != null) {
                        MapData mapData = new MapData();
                        mapData.setPsi(psi.get(location));
                        mapData.setLat(metadata.getLat());
                        mapData.setLng(metadata.getLng());
                        mapDataArr.add(mapData);
                    }
                }
            }
        }

        return mapDataArr;
    }
}
