package pjwstk.edu.pl.s27619.source.generators;

import java.util.Random;

public class UniqueNumberGenerator {

    /**
     * Methods returns random student group number
     *
     * @return object of type String, which contains student group number and have format "WXXXX"
     */
    public static String generateGroupNumber() {
        Random random = new Random();
        int maxValue = 4000;
        int minValue = 1000;
        int randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
        return "W" + String.format("%04d", randomValue);
    }

    /**
     * Methods returns random department number
     *
     * @return object of type String, which contains department number and have format "DEPXXXX"
     */
    public static String generateDepartmentNumber() {
        Random random = new Random();
        int maxValue = 4000;
        int minValue = 1000;
        int randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
        return "DEP" + String.format("%04d", randomValue);
    }
}
