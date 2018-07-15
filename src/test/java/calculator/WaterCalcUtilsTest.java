package calculator;

import org.junit.Test;


public class WaterCalcUtilsTest {

    @Test
    public void generateLandscape() {
        int[] landscape = WaterCalcUtils.generateLandscape();
        WaterCalcUtils.validateLandscape(landscape);
    }

    @Test (expected=ValidationException.class)
    public void shouldFailCauseOfNullLandscape() {
        WaterCalcUtils.validateLandscape(null);
    }

    @Test (expected=ValidationException.class)
    public void shouldFailCauseOfTooBigLandscapeLength() {
        int[] landscape = new int [WaterCalcUtils.MAX_LENGTH + 1];
        WaterCalcUtils.validateLandscape(landscape);
    }

    @Test (expected=ValidationException.class)
    public void shouldFailCauseOfTooBigLandscapeHeight() {
        int[] landscape = {5,2,3,4,5,4,0,3,WaterCalcUtils.MAX_HEIGHT + 1};
        WaterCalcUtils.validateLandscape(landscape);
    }

    @Test (expected=ValidationException.class)
    public void shouldFailCauseOfTooSmallLandscapeHeight() {
        int[] landscape = {5,2,3,4,5,4,0,3,WaterCalcUtils.MIN_HEIGHT - 1};
        WaterCalcUtils.validateLandscape(landscape);
    }
}