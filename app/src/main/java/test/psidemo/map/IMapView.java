package test.psidemo.map;

import java.util.List;

/**
 * Map View.
 */

public interface IMapView {
    /*
     * Updates the map with a list of MapData.
     *
     * @param mapData The List of MapData to update the map with.
     */
    void update(List<MapData> mapData);
}
