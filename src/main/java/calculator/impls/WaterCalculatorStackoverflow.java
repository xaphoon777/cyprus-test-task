package calculator.impls;

import calculator.IWaterCalculator;
import calculator.WaterCalcUtils;

public class WaterCalculatorStackoverflow implements IWaterCalculator {

    public long calculateWaterAmount(int[] landscape) {

        WaterCalcUtils.validateLandscape(landscape);

        int n = landscape.length;

        int maxValue = 0;
        int total = 0;
        int[] lookAhead = new int[n];

        for (int i = 0; i < n; i++) {
            if (landscape[i] > maxValue) maxValue = landscape[i];
            lookAhead[i] = maxValue;
        }

        maxValue = 0;
        for (int i = n - 1; i >= 0; i--) {
            // If the input is greater than or equal to the max, all water escapes.
            if (landscape[i] >= maxValue) {
                maxValue = landscape[i];
            } else {
                if (maxValue > lookAhead[i]) {
                    // Make sure we don't run off the other side.
                    if (lookAhead[i] > landscape[i]) {
                        total += lookAhead[i] - landscape[i];
                    }
                } else {
                    total += maxValue - landscape[i];
                }
            }
        }
        return total;
    }
}
