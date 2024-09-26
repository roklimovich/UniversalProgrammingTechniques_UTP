package pjwstk.edu.pl.s27619.source.people;

import pjwstk.edu.pl.s27619.source.extents.StudentExtent;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Student extends Person{
    private String studentBookNumber;
    public Student(String pesel, String surname, String firstName, LocalDate birthDate, Nationality nationality,
                   String studentBookNumber) {
        super(pesel, surname, firstName, birthDate, nationality);
        this.studentBookNumber = studentBookNumber;
        StudentExtent.addStudent(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentBookNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        Student student = (Student) obj;
        return Objects.equals(studentBookNumber, student.studentBookNumber);
    }

    @Override
    public String toString() {
        return super.toString() + " Student book number: " + studentBookNumber;
    }

}
