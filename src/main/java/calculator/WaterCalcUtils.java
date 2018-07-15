package calculator;

import java.util.Random;

public class WaterCalcUtils {

    public static final int MAX_LENGTH = 32_000;
    public static final int MIN_HEIGHT = 0;
    public static final int MAX_HEIGHT = 32_000;

    public static void validateLandscape(int[] landscape) throws ValidationException {

        if (landscape == null) {
            throw new ValidationException("landscape is null");
        }

        if (landscape.length > MAX_LENGTH) {
            throw new ValidationException("landscape length: " + landscape.length + " is bigger than: " + MAX_LENGTH);
        }

        for (int i : landscape) {
            if (i > MAX_HEIGHT || i < MIN_HEIGHT) {
                throw new ValidationException("landscape height: " + i + " is out of range: " + MIN_HEIGHT + " to " + MAX_HEIGHT);
            }
        }
    }

    public static int[] generateLandscape() {
        Random random = new Random();
        return generateLandscape(random.nextInt(MAX_LENGTH));
    }

    public static int[] generateLandscape(int size) {

        Random random = new Random();

        int[] landscape = new int[size];

        for (int position = 0; position < landscape.length; position++) {
            landscape[position] = random.nextInt(MAX_HEIGHT - MIN_HEIGHT);
        }

        return landscape;

    }

}
