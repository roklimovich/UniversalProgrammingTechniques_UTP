package pjwstk.edu.pl.s27619.source.generators;

import java.util.Random;

public class NameGenerator {
    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy",
            "Jack", "Katherine", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Rachel", "Samuel", "Tina",
            "Ulysses", "Violet", "William", "Xander", "Yara", "Zane", "Sophia", "Ethan", "Ava"};

    /**
     * Methods returns random name from the given array with names
     *
     * @return object of type String, which contains name
     */
    public static String getRandomName() {

        return NAMES[new Random().nextInt(NAMES.length)];
    }

}
