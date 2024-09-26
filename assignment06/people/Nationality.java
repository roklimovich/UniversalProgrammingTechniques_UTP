package pjwstk.edu.pl.s27619.source.people;

import java.text.Collator;
import java.util.Locale;
import java.util.Random;

public enum Nationality {
    POLISH(new Locale("pl")),
    UKRANIAN(new Locale("ua")),
    BELARUSSIAN(new Locale("by")),
    SLOVAK(new Locale("sk")),
    LITHUANIAN(new Locale("lt")),
    LATVIAN(new Locale("lv")),
    BRITISH(new Locale("en", "GB")),
    INDIAN(new Locale("en", "IN")),
    CHINESE(new Locale("zh", "CN")),
    VIETNAMESE(new Locale("vi", "VN"));

    private Locale locale;
    private Collator collator;

    Nationality(Locale locale) {
        this.locale = locale;
        collator = Collator.getInstance(locale);
    }

    /**
     * Method generates random nationality for person
     *
     * @return object of type Nationality
     */
    public static Nationality generateNationality() {
        Random random = new Random();
        Nationality[] nationalities = Nationality.values();
        return nationalities[random.nextInt(nationalities.length)];
    }

    public Collator getCollator() {
        return collator;
    }

}
