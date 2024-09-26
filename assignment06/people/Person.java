package pjwstk.edu.pl.s27619.source.people;

import pjwstk.edu.pl.s27619.source.extents.PersonExtent;

import java.text.Collator;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public abstract class Person implements Comparable<Person>{
    private static Collator collator = Nationality.POLISH.getCollator();
    private String pesel;
    private String surname;
    private String firstName;
    private LocalDate birthDate;
    private Nationality nationality;

    public Person(String pesel, String surname, String firstName, LocalDate birthDate, Nationality nationality) {
        this.pesel = pesel;
        this.surname = surname;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        PersonExtent.addPerson(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, surname, firstName, birthDate, nationality);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Person person = (Person) obj;

        return Objects.equals(pesel, person.pesel) && Objects.equals(surname, person.surname)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(birthDate, person.birthDate)
                && nationality == person.nationality;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        return Comparator.comparing(Person::getSurname, collator)
                .thenComparing(Person::getFirstName, collator)
                .thenComparing(Person::getBirthDate)
                .compare(this, anotherPerson);
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    @Override
    public String toString() {
        return "Name: " + firstName + "; Surname: " + surname + "; Birth date: " + birthDate + "; Nationality: " +
                nationality + "; PESEL: " + pesel + ";";
    }
}
