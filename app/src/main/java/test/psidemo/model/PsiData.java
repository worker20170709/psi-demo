package test.psidemo.model;

/**
 * Holds the Psi data.
 */

public class PsiData {
    private PsiReading mPsiTwentyFourHourly;

    public void setPsiTwentyFourHourly(PsiReading reading) {
        mPsiTwentyFourHourly = reading;
    }

    public PsiReading getPsiTwentyFourHourly() {
        return  mPsiTwentyFourHourly;
    }
}
