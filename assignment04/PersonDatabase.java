package pjwstk.edu.pl.s27619.source;

import pjwstk.edu.pl.s27619.source.comparotrs.BirthDateComparator;
import pjwstk.edu.pl.s27619.source.comparotrs.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

    private final List<Person> people;

    public PersonDatabase(List<Person> people) {

        this.people = people;
    }

    /**
     * Method sorts the list of people by first name
     *
     * @return list of people sorted by first name
     */
    public List<Person> sortedByFirstName() {
        // external rule for ordering (based on Comparator --- FirstNameComparator)
        Comparator<Person> firstNameComparator = new FirstNameComparator();

        List<Person> sortedPeopleList = new ArrayList<>();
        sortedPeopleList.addAll(people);
        sortedPeopleList.sort(firstNameComparator);

        return sortedPeopleList;
    }

    /**
     * Method sorts the list of people by first name and date
     *
     * @return list of people sorted by first name and birthdate
     */
    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        // natural order (Comparable)
        List<Person> sortedPeopleList = new ArrayList<>();
        sortedPeopleList.addAll(people);
        sortedPeopleList.sort(Comparator.naturalOrder());

        return sortedPeopleList;
    }

    /**
     * Method sorts the list of people by birthdate
     *
     * @return list of people sorted by birthdate
     */
    public List<Person> sortedByBirthdate() {
        // external rule for ordering (based on Comparator --- BirthdateComparator)
        Comparator<Person> birthDateComparator = new BirthDateComparator();

        List<Person> sortedPeopleList = new ArrayList<>();
        sortedPeopleList.addAll(people);
        sortedPeopleList.sort(birthDateComparator);

        return sortedPeopleList;
    }

    /**
     * Method sorts the list of people who was born on day
     *
     * @param date contains date that we need to find people with the same birthdate
     * @return list of person with the same birthdate
     */
    public List<Person> bornOnDay(Date date) {
        List<Person> personList = sortedByFirstName();

        Map<Date, List<Person>> grouping = personList.stream()
                .collect(Collectors.groupingBy(Person::getBirthdate,
                        TreeMap::new,
                        Collectors.mapping(person -> person, Collectors.toList())));

        return grouping.get(date);
    }

    // ASSIGNMENT 8

    /**
     * Method serialize the state of an object into a byte stream
     *
     * @param output object of type DataOutputStream contains output file
     * @throws Assignment08Exception, if we can't serialize
     */
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        this.people.forEach(person -> {
            try {
                person.serialize(output);
            } catch (Assignment08Exception e) {
                System.out.println("Exception in method serialize in class PersonDatabase was thrown");
            }
        });
    }

    /**
     * This method deserialize the byte stream is used to recreate the actual Java object in memory
     *
     * @param input object of type DataInputStream which contains input file
     * @return object of type PersonDatabase
     * @throws Assignment08Exception, if we can't deserialize
     */
    public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
        List<Person> personList = new ArrayList<>();

        try {
            while (input.available() > 0) {
                personList.add(Person.deserialize(input));
            }
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e);
        }

        return new PersonDatabase(personList);
    }

    public List<Person> getPeople() {
        return people;
    }

}