package calculator;

import calculator.impls.WaterCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;
import java.util.TreeSet;

public class WaterCalculatorTest {

    private WaterCalculator calculator = new WaterCalculator();

    @Test
    public void positiveTestFromTask() {
        int[] landscape = {5,2,3,4,5,4,0,3,1};
        Assert.assertEquals( 9, calculator.calculateWaterAmount(landscape));
    }

    @Test
    public void parseLandscapeTest() {
        int[] landscape = {5,2,3,4,5,4,0,3,1};
        TreeMap<Integer, TreeSet<Integer>> parsed = calculator.parseLandscape(landscape);
        System.out.println(parsed);
    }
}