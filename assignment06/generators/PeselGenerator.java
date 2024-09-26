package pjwstk.edu.pl.s27619.source.generators;

import java.time.LocalDate;
import java.util.Random;

public class PeselGenerator {

    /**
     * Method generates valid PESEL number for person
     *
     * @param birthDate contains info about birthdate
     * @return object of type String, with valid PESEL number
     */
    public static String generatePeselNumber(LocalDate birthDate) {
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue();
        int day = birthDate.getDayOfMonth();
        int century;

        // Calculate century for adding necessary values to understand in which century person was born
        if (year % 100 == 0) {
            century = year / 100;
        } else {
            century = (year / 100) + 1;
        }

        switch (century) {
            case 19:
                month += 80;
                break;
            case 20:
                break;
            case 21:
                month += 20;
                break;
            case 22:
                month += 40;
                break;
            case 23:
                month += 60;
                break;
        }

        int lastTwoDigitOfYear = year % 100; // Contains info about last two digit of year
        int serialNumber = generateSerialNumber(); // Contains random serial number

        // Collect all known information to one object of type String and use special format to get correct result
        // without control digit
        String result = String.format("%02d", lastTwoDigitOfYear) + String.format("%02d", month)
                + String.format("%02d", day) + serialNumber;

        int controlDigitValue = generateControlDigit(result); // Contains control digit value
        result += controlDigitValue; // Add founded control value to our generated PESEL

        return result;
    }

    /**
     * Method to generate control digit for PESEL
     *
     * @param pesel contains information about PESEL without control digit
     * @return object of type int, which shows control digit for PESEL
     */
    public static int generateControlDigit(String pesel) {
        // Special numbers which Polish government use to decode all values in PESEL
        int[] specialSeq = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int result = 0;

        for (int i = 0; i < specialSeq.length; i++) {
            int digit = Character.getNumericValue(pesel.charAt(i));
            result += digit * specialSeq[i];
        }

        result %= 10; // The reminder will be our control digit and used to valid our PESEL

        return result;
    }

    /**
     * Method generates random serial number for PESEL
     *
     * @return object of type int in a range from 1000 to 9999
     */
    public static int generateSerialNumber() {
        Random random = new Random();
        int maxValue = 9999;
        int minValue = 1000;
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }

    /**
     * Method returns valid PESEL number from given birthdate
     *
     * @param birthDate variable which contains day in the format "yyyy-MM-dd"
     * @return object of type String, which contains valid PESEL
     */
    public static String getPeselNumber(LocalDate birthDate) {
        return generatePeselNumber(birthDate);
    }

}
