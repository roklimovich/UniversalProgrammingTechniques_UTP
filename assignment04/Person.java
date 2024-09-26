package pjwstk.edu.pl.s27619.source;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public final class Person implements Comparable<Person> {

    private final String firstName;
    private final String surname;
    private final Date birthdate;

    public Person(String firstName, String surname, Date birthdate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    // ASSIGNMENT 8

    /**
     * Method serialize the state of an object into a byte stream
     *
     * @param output object of type DataOutputStream contains output file
     * @throws Assignment08Exception, if we can't serialize
     */
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        // serialize birth date with getTime() method
        // encapsulate IOException in Assignment08Exception
        try {
            output.writeUTF(this.firstName);
            output.writeUTF(this.surname);
            output.writeLong(this.birthdate.getTime());
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e);
        }
    }

    /**
     * This method deserialize the byte stream is used to recreate the actual Java object in memory
     *
     * @param input object of type DataInputStream which contains input file
     * @return object of type PersonDatabase
     * @throws Assignment08Exception, if we can't deserialize
     */
    public static Person deserialize(DataInputStream input) throws Assignment08Exception {
        Person person;

        try {
            person = new Person(
                    input.readUTF(), input.readUTF(), new Date(input.readLong()));
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e);
        }

        return person;
    }

    @Override
    public int compareTo(Person otherPerson) {
        // natural order based on:
        // (1) surname;
        // (2) first name;
        // (3) birthdate.

        int compareResult;

        compareResult = this.surname.compareTo(otherPerson.getSurname());
        if (compareResult != 0) {
            return compareResult;
        }

        compareResult = this.firstName.compareTo(otherPerson.getFirstName());
        if (compareResult != 0) {
            return compareResult;
        }

        return this.birthdate.compareTo(otherPerson.getBirthdate());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

}
