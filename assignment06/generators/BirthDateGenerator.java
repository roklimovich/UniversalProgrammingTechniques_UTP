package pjwstk.edu.pl.s27619.source.generators;

import java.time.LocalDate;
import java.time.Period;

public class BirthDateGenerator {

    /**
     * Method generates random birthdate in given range of years
     *
     * @param minAge value which contains info about min range of years
     * @param maxAge value which contains info about max range of years
     * @return object of type LocalDate, which contains random generated birthdate
     */
    public static LocalDate generateRandomBirthDate(int minAge, int maxAge) {
        // Calculate the minimum and maximum birthdates based on the provided age range
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.minus(Period.ofYears(maxAge));
        LocalDate maxDate = currentDate.minus(Period.ofYears(minAge));

        // Convert local dates to type long, to get random values and generate birthdate
        long minDays = minDate.toEpochDay();
        long maxDays = maxDate.toEpochDay();

        // Generate a random number of days between minDays and maxDays
        long randomDays = minDays + (long) (Math.random() * (maxDays - minDays));

        return LocalDate.ofEpochDay(randomDays);
    }

}
