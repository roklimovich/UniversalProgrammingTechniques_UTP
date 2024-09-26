package pjwstk.edu.pl.s27619.source.comparotrs;

import pjwstk.edu.pl.s27619.source.Person;

import java.util.Comparator;

public final class BirthDateComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {

        return person1.getBirthdate().compareTo(person2.getBirthdate());
    }
}
