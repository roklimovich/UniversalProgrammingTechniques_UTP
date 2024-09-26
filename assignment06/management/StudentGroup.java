package pjwstk.edu.pl.s27619.source.management;

import pjwstk.edu.pl.s27619.source.extents.StudentGroupExtent;
import pjwstk.edu.pl.s27619.source.people.Student;

import java.util.List;
import java.util.Objects;

public class StudentGroup implements Comparable<StudentGroup> {
    private String name;
    private List<Student> studentList;

    public StudentGroup(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
        StudentGroupExtent.addStudentGroup(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        StudentGroup studentGroup = (StudentGroup) obj;
        return Objects.equals(name, studentGroup.name)
                && Objects.equals(studentList, studentGroup.studentList);
    }

    @Override
    public int compareTo(StudentGroup o) {
        return 0;
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public String toString() {
        return "Group name: " + name;
    }
}
