package test.psidemo.model;

import java.util.Map;

/**
 * Psi data item.
 */

public class PsiDataItem {
    private String mTimestamp;
    private String mUpdateTmestamp;
    private Map<String, Double> mPsiTwentyFourHourly;

    public void setPsiTwentyFourHourly(Map<String, Double> reading) {
        mPsiTwentyFourHourly = reading;
    }

    public Map<String, Double> getPsiTwentyFourHourly() {
        return  mPsiTwentyFourHourly;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public String getTimeStamp() {
        return mTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        mUpdateTmestamp = updateTimestamp;
    }

    public String getUpdateTimeStamp() {
        return mUpdateTmestamp;
    }
}
