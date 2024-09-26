package pjwstk.edu.pl.s27619.source.generators;

import java.util.Random;

public class StudentBookNumberGenerator {

    /**
     * Methods returns random student number in a range from 10000 to 99999
     *
     * @return object of type String, which contains student number and have format "sXXXXX"
     */
    public static String generateStudentNumber() {
        Random random = new Random();
        int maxValue = 99999;
        int minValue = 10000;
        int randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
        return "s" + String.format("%05d", randomValue);
    }

}
