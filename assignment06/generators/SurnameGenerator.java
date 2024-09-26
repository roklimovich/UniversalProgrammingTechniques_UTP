package pjwstk.edu.pl.s27619.source.generators;

import java.util.Random;

public class SurnameGenerator {
    private static final String[] SURNAMES = {"Johnson", "Smith", "Brown", "Lee", "Davis", "Wilson", "Martinez",
            "Clark", "Taylor", "Turner", "White", "Hall", "Harris", "Walker", "Scott", "Lewis", "King", "Baker",
            "Young", "Adams", "Patel", "Mitchell", "Green", "Carter", "Johnson", "Robinson", "Anderson", "Martinez",
            "Davis", "Wilson"};

    /**
     * Methods returns random surname from the given array with surnames
     *
     * @return object of type String, which contains surname
     */
    public static String getRandomSurname() {

        return SURNAMES[new Random().nextInt(SURNAMES.length)];
    }
}
