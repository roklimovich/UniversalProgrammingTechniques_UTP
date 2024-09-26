package pjwstk.edu.pl.s27619.source.management;

import pjwstk.edu.pl.s27619.source.extents.SubjectExtent;
import pjwstk.edu.pl.s27619.source.people.Student;
import pjwstk.edu.pl.s27619.source.people.Teacher;

import java.util.Comparator;
import java.util.List;

public class Subject implements Comparable<Subject> {
    private String name;
    private Department supervisingDepartment;
    private Teacher lecturer;
    private List<Student> studentList;

    public Subject(String name, Department supervisingDepartment, Teacher lecturer, List<Student> studentList) {
        this.name = name;
        this.supervisingDepartment = supervisingDepartment;
        this.lecturer = lecturer;
        this.studentList = studentList;
        SubjectExtent.addSubject(this);
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public int compareTo(Subject anotherSubject) {
        return Comparator.comparing(Subject::getName).compare(this, anotherSubject);
    }

    @Override
    public String toString() {
        return "Name: " + name + " " + supervisingDepartment + " " + lecturer;
    }
}
