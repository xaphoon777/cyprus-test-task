package calculator.impls;

import calculator.IWaterCalculator;
import calculator.WaterCalcUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WaterCalculatorByOlga implements IWaterCalculator {

    // 0  1  2 ->3   4  5  6<- 7  8   9
    //int[] arr = new int[]{5, 7, 10, 15, 2, 4, 11, 7, 1 , 5};

    private Integer fromArr(int[] arr, Integer summ) {

//        System.out.println(Arrays.toString(arr));
        if (arr.length <= 2) {
            return summ;
        }

        int max1 = IntStream.of(arr).summaryStatistics().getMax();
        Integer ind1 = IntStream.of(arr).boxed().collect(Collectors.toList()).indexOf(max1);

        // ----------------------------------
        arr[ind1] = -1;
        int max2 = IntStream.of(arr).summaryStatistics().getMax();
        Integer ind2 = Arrays.stream(arr).boxed().collect(Collectors.toList()).indexOf(max2);

        arr[ind1] = max1; // magic

        Integer rightMaxInd = Math.max(ind1, ind2);
        Integer leftMaxInd = Math.min(ind1, ind2);

//        System.out.println(String.format(">>> Max Index1: %d", rightMaxInd));
//        System.out.println(String.format(">>> Max Index2: %d", leftMaxInd));
        int[] forCountArr = Arrays.copyOfRange(arr, leftMaxInd + 1, rightMaxInd);

//        System.out.println(">> " + Arrays.toString(forCountArr));;

        // Площадь прямоугольника без рамок
        Integer sAllSquare = (forCountArr.length) * Math.min(max1, max2);

        // Сумма кирпичей внутри прямоугольника
        Integer sFullingSq = Arrays.stream(forCountArr).sum();

//        System.out.println(">> All " + sAllSquare);
//        System.out.println(">> Only Full " + sFullingSq);

        // Объем воды
        int diffW = sAllSquare - sFullingSq;
//        System.out.println(">>>> Diff " + diffW);

        // Массив оставшегося слева вместе со столбиком
        int[] arrLeft = Arrays.copyOfRange(arr, 0, leftMaxInd + 1);

        // Массив оставшегося справа вместе со столбиком
        int[] arrRight = Arrays.copyOfRange(arr, rightMaxInd, arr.length);

//        System.out.println("Right: " + Arrays.toString(arrRight));
//        System.out.println("Left : " + Arrays.toString(arrLeft));
//        int[] arr1 = IntStream.of(arrLeft).skip(rightMaxInd).toArray();//boxed().collect(Collectors.toList()).toArray(new Integer[4]);
//        System.out.println(">> New Arr = " + Arrays.toString(arr1));

        return fromArr(arrLeft, summ + diffW) + fromArr(arrRight, 0);
    }

    @Override
    public long calculateWaterAmount(int[] landscape) {
        //        System.out.println(Arrays.toString(landscape));
//        System.out.println(">>> Summ " + summ);

        WaterCalcUtils.validateLandscape(landscape);

        return fromArr(landscape, 0);
    }
}