package calculator.impls;

import calculator.IWaterCalculator;
import calculator.ValidationException;
import calculator.WaterCalcUtils;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class WaterCalculator implements IWaterCalculator {

    private long wallsAmount;

    public long calculateWaterAmount(int[] landscape) {
        WaterCalcUtils.validateLandscape(landscape);

        wallsAmount = 0;
        long waterAndWallsAmount = 0;
        TreeMap<Integer, TreeSet<Integer>> maximumsMap = parseLandscape(landscape);

        int leftPosition;
        int rightPosition;

        // Determine maximums
        Map.Entry<Integer, TreeSet<Integer>> firstEntry = maximumsMap.firstEntry();
        TreeSet<Integer> theHighestMaximumPositions = firstEntry.getValue();
        if (theHighestMaximumPositions.size() >= 1) {
            leftPosition = theHighestMaximumPositions.first();
            rightPosition = theHighestMaximumPositions.last();
            Integer maximumHeight = firstEntry.getKey();
            waterAndWallsAmount += (rightPosition - leftPosition + 1) * maximumHeight;
            maximumsMap.remove(firstEntry.getKey());
        } else {
            throw new ValidationException("No landscape found");
        }

        boolean leftComplete = false;
        boolean rightComplete = false;

        int size = landscape.length;
        for (Map.Entry<Integer, TreeSet<Integer>> entry : maximumsMap.entrySet()) {
            int height = entry.getKey();
            TreeSet<Integer> positions = entry.getValue();

            for (Integer position : positions) {
                if (position == 0) {
                    leftComplete = true;
                }
                if (position == size - 1) {
                    rightComplete = true;
                }
                if (position < leftPosition) {
                    // Append amount from left
                    waterAndWallsAmount += (leftPosition - position) * height;
                    leftPosition = position;

                } else {
                    if (position > rightPosition) {
                        // Append amount from right
                        waterAndWallsAmount += (position - rightPosition) * height;
                        rightPosition = position;
                    }
                }
            }

            if (leftComplete && rightComplete) {
                break;
            }
        }

        long waterAmount = waterAndWallsAmount - wallsAmount;

        return waterAmount;
    }

    public TreeMap<Integer, TreeSet<Integer>> parseLandscape(int[] landscape) {

        TreeMap<Integer, TreeSet<Integer>> maximumsMap = new TreeMap<>(Collections.reverseOrder());

        for (int position = 0; position < landscape.length; position++) {
            int height = landscape[position];
            TreeSet<Integer> maximumsEntry = maximumsMap.get(height);
            if (maximumsEntry == null) {
                maximumsEntry = new TreeSet<>();
            }
            maximumsEntry.add(position);
            maximumsMap.put(height, maximumsEntry);

            wallsAmount += height;
        }
        return maximumsMap;
    }
}
