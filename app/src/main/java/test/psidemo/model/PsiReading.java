package test.psidemo.model;

/**
 * Holds the Psi readings.
 */

public class PsiReading {
    private double mNational;
    private double mCentral;
    private double mNorth;
    private double mSouth;
    private double mEast;
    private double mWest;

    private void setNational(double national) {
        mNational = national;
    }

    private double getNational() {
        return mNational;
    }

    private void setCentral(double central) {
        mCentral = central;
    }

    private double getCentral() {
        return mCentral;
    }

    private void setNorth(double north) {
        mNorth = north;
    }

    private double getNorth() {
        return mNorth;
    }

    private void setSouth(double south) {
        mSouth = south;
    }

    private double getSouth() {
        return mSouth;
    }


    private void setEast(double east) {
        mEast = east;
    }

    private double getEast() {
        return mEast;
    }

    private void setWest(double west) {
        mWest = west;
    }

    private double getWest() {
        return mWest;
    }
}
