package test.psidemo.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;

import test.psidemo.model.PsiData;
import test.psidemo.model.PsiDataItem;
import test.psidemo.model.RegionMetaData;

/**
 * Data parser implementation. Parses JSON data which is expected to be in a form like:
 * {
 *   "region_metadata": [
 *     {
 *       "name": "west",
 *       "label_location": {
 *         "latitude": 1.35735,
 *         "longitude": 103.7
 *       }
 *     },
 *     ...
 *   ],
 *   "items": [
 *     {
 *       "timestamp": "2017-07-09T13:00:00+08:00",
 *       "update_timestamp": "2017-07-09T13:06:18+08:00",
 *       "readings": {
 *         "psi_twenty_four_hourly": {
 *           "west": 52,
 *           "national": 54,
 *           "east": 50,
 *           "central": 49,
 *           "south": 54,
 *           "north": 53
 *         }
 *       }
 *     }
 *   ],
 *   "api_info": {
 *     "status": "healthy"
 *   }
 * }
 *
 * The parsing is lenient, in that the result might contain imcomplete data. Also a null result
 * might be returned if there is an Exception.
 */

public class DataParserImpl implements IDataParser {
    @Override
    public PsiData parse(String data) {
        PsiData psiData = new PsiData();
        try {
            JSONObject object = (JSONObject)new JSONTokener(data).nextValue();

            // Region metadata.
            JSONArray regionMetadataArrJson = object.getJSONArray("region_metadata");
            HashMap<String, RegionMetaData> metadataMap = new HashMap<>();
            for (int i = 0; i < regionMetadataArrJson.length(); i++) {
                JSONObject regionMetadataJson = regionMetadataArrJson.getJSONObject(i);
                JSONObject labelLocationJson = regionMetadataJson.getJSONObject("label_location");

                RegionMetaData metadata = new RegionMetaData();
                metadata.setLat(labelLocationJson.getDouble("latitude"));
                metadata.setLng(labelLocationJson.getDouble("longitude"));
                metadataMap.put(regionMetadataJson.getString("name"), metadata);
            }
            psiData.setRegionMetadata(metadataMap);

            // Item data.
            JSONArray itemsArrJson = object.getJSONArray("items");
            ArrayList<PsiDataItem> dataItems = new ArrayList<>();
            for (int i = 0; i < itemsArrJson.length(); i++) {
                JSONObject psiItemJson = itemsArrJson.getJSONObject(i);
                PsiDataItem dataItem = new PsiDataItem();
                dataItem.setTimestamp(psiItemJson.getString("timestamp"));
                dataItem.setUpdateTimestamp(psiItemJson.getString("update_timestamp"));
                JSONObject readingsJson = psiItemJson.getJSONObject("readings");
                JSONObject psiJson = readingsJson.getJSONObject("psi_twenty_four_hourly");
                HashMap<String, Double> psiReadings = new HashMap<>();

                psiReadings.put("national", psiJson.getDouble("national"));
                psiReadings.put("west", psiJson.getDouble("west"));
                psiReadings.put("east", psiJson.getDouble("east"));
                psiReadings.put("central", psiJson.getDouble("central"));
                psiReadings.put("south", psiJson.getDouble("south"));
                psiReadings.put("north", psiJson.getDouble("north"));
                dataItem.setPsiTwentyFourHourly(psiReadings);
                dataItems.add(dataItem);
            }


            psiData.setDataItems(dataItems);

            return psiData;
        } catch (JSONException e) {
            // Do nothing.
        }

        return null;
    }
}
