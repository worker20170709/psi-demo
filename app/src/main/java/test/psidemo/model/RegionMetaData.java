package test.psidemo.model;

/**
 * Contains the region meta data.
 */

public class RegionMetaData {
    private String mName;
    private double mLat;
    private double mLng;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

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
