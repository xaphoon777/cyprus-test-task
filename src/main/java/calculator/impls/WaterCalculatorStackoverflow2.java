package calculator.impls;

import calculator.IWaterCalculator;
import calculator.WaterCalcUtils;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class WaterCalculatorStackoverflow2 implements IWaterCalculator {

    public long calculateWaterAmount(int[] landscape) {

        WaterCalcUtils.validateLandscape(landscape);

        int n = landscape.length;

        int hl[] = new int[n];
        int hr[] = new int[n]; //highest-left and highest-right

        for (int i = 0; i < n; i++) {
            hl[i] = max(landscape[i], (i != 0) ? hl[i - 1] : 0);
        }

        for (int i = n - 1; i >= 0; i--) {
            hr[i] = max(landscape[i], i < (n - 1) ? hr[i + 1] : 0);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += min(hl[i], hr[i]) - landscape[i];
        }

        return ans;
    }


}
