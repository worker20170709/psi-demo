package test.psidemo.model;

/**
 * Contains the region meta data.
 */

public class RegionMetaData {
    private double mLat;
    private double mLng;

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLat() {
        return mLat;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public double getLng() {
        return mLng;
    }
}
