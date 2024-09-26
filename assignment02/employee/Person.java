package pjwstk.edu.pl.s27619.employee;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    //
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // the type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date

    private final String firstName; // backing field
    private final String surname;
    private final LocalDate birthDate;

    protected Person(String firstName, String surname, LocalDate birthDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public short getAge() {
        // TO BE IMPLEMENTED CORRECTLY
        return (short) Period.between(birthDate, LocalDate.now()).getYears();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // ASSIGNMENT 3

    /**
     * Method compare age with other person's age
     *
     * @param person contains info about given person
     * @return integer value of comparing
     */
    public int compareAge(Person person) {
        return birthDate.compareTo(person.getBirthDate());
    }

    /**
     * Method returns boolean result of comparing age and given person's age
     *
     * @param person contains info about given person
     * @return true if older, and false if not
     */
    public boolean isOlder(Person person) {
        return compareAge(person) < 0;
    }

    /**
     * Method returns boolean result of comparing age and given person's age
     *
     * @param person contains info about given person
     * @return true if younger, and false if not
     */
    public boolean isYounger(Person person) {
        return compareAge(person) > 0;
    }

}