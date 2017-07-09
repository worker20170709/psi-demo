package test.psidemo.map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Map data for display on map.
 */

public class MapData implements Parcelable {
    private double mLat;
    private double mLng;
    private double mPsi;
    private boolean mIsNational;

    public MapData() {
        // Empty ctor.
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public double getPsi() {
        return mPsi;
    }

    public void setPsi(double psi) {
        mPsi = psi;
    }

    public void setIsNational(boolean isNational) {
        mIsNational = isNational;
    }

    public boolean getIsNational() {
        return mIsNational;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(mLat);
        out.writeDouble(mLng);
        out.writeDouble(mPsi);
        out.writeInt(mIsNational ? 1 : 0);
    }

    public static final Parcelable.Creator<MapData> CREATOR
            = new Parcelable.Creator<MapData>() {
        public MapData createFromParcel(Parcel in) {
            return new MapData(in);
        }

        public MapData[] newArray(int size) {
            return new MapData[size];
        }
    };

    private MapData(Parcel in) {
        mLat = in.readDouble();
        mLng = in.readDouble();
        mPsi = in.readDouble();
        mIsNational = in.readInt() == 1;
    }
}
