package pjwstk.edu.pl.s27619.source.extents;

import pjwstk.edu.pl.s27619.source.people.Person;

import java.util.*;

public class PersonExtent {
    private static Set<Person> personSet = new HashSet<>();
    private static List<Person> personList = new ArrayList<>();

    /**
     * Method adds person to set and list of all persons
     *
     * @param person given object which should be added
     */
    public static void addPerson(Person person) {
        personSet.add(person);
        personList.add(person);
        personList.sort(Comparator.naturalOrder());
    }

}
