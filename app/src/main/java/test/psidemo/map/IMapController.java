package test.psidemo.map;

/**
 * Updates the Map data.
 */

public interface IMapController {
    /*
     * Gets map data.
     *
     * @param mapView The consumer of the map data.
     */
    void getMapData(IMapView mapView);
}
