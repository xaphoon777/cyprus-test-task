package calculator;

import calculator.impls.WaterCalculator;
import calculator.impls.WaterCalculatorByOlga;
import calculator.impls.WaterCalculatorStackoverflow;
import calculator.impls.WaterCalculatorStackoverflow2;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class BenchmarkTest {

    private IWaterCalculator calculator = new WaterCalculator();
    private IWaterCalculator olgaCalculator = new WaterCalculatorByOlga();
    private IWaterCalculator waterCalculatorStackoverflow2 = new WaterCalculatorStackoverflow2();
    private IWaterCalculator waterCalculatorStackoverflow = new WaterCalculatorStackoverflow();

    @Test
    public void multipleCrossTest() {
        for (int i = 0; i < 1_000; i++) {
            int[] landscape = WaterCalcUtils.generateLandscape();
            crossTest(landscape);
        }
    }

    @Test
    public void singleCrossTest() {
        int[] landscape = {5,2,3,4,5,4,0,3,1,5,2,0,2};
        crossTest(landscape);
    }


    private void crossTest(int[] landscape) {

        long myResult = calculator.calculateWaterAmount(landscape);
        long stack1Result = waterCalculatorStackoverflow.calculateWaterAmount(landscape);
        long stack2Result = waterCalculatorStackoverflow2.calculateWaterAmount(landscape);
        long olgaResult = olgaCalculator.calculateWaterAmount(landscape);
        Assert.assertEquals(myResult, olgaResult);
        Assert.assertEquals(myResult, stack1Result);
        Assert.assertEquals(myResult, stack2Result);
    }


    @Test
    public void benchmark() {

        int times = 50_000;

        List<int[]> lands = new ArrayList<>(times);

        LocalDateTime begin = LocalDateTime.now();

        for (int i = 0; i < times; i++) {
            lands.add(WaterCalcUtils.generateLandscape());
        }

        printTimeDiff(begin, "Generating landscapes: ");

        begin = LocalDateTime.now();
        for (int[] landscape : lands) {
            waterCalculatorStackoverflow2.calculateWaterAmount(landscape);
        }
        printTimeDiff(begin, "Stack 2 result: ");

        begin = LocalDateTime.now();
        for (int[] landscape : lands) {
            waterCalculatorStackoverflow.calculateWaterAmount(landscape);
        }
        printTimeDiff(begin, "Stack result: ");
    }

    private void printTimeDiff(LocalDateTime begin, String msg) {

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(begin, end);

        long s = duration.getSeconds();
        System.out.println(msg + String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60)));
    }

}