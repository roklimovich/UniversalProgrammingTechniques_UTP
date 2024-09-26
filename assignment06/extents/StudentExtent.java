package pjwstk.edu.pl.s27619.source.extents;

import pjwstk.edu.pl.s27619.source.people.Student;

import java.util.*;

public class StudentExtent {
    private static Set<Student> studentSet = new HashSet<>();
    private static List<Student> studentList = new ArrayList<>();

    /**
     * Method adds student to set and list of all students
     *
     * @param student given object which should be added
     */
    public static void addStudent(Student student) {
        studentSet.add(student);
        studentList.add(student);
        studentList.sort(Comparator.naturalOrder());
    }

    /**
     * Method returns random student from the list of all students
     *
     * @return random object of type Student from list
     */
    public static Student getRandomStudent() {
        return studentList.get(new Random().nextInt(studentList.size()));
    }

}
