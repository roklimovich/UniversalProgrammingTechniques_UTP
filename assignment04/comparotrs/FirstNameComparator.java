package pjwstk.edu.pl.s27619.source.comparotrs;

import pjwstk.edu.pl.s27619.source.Person;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {

        return person1.getFirstName().compareTo(person2.getFirstName());
    }
}