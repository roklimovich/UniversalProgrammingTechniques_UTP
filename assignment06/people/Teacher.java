package pjwstk.edu.pl.s27619.source.people;

import pjwstk.edu.pl.s27619.source.extents.TeacherExtent;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher extends Person{
    private AcademicDegree academicDegree;

    public Teacher(String pesel, String surname, String firstName, LocalDate birthDate, Nationality nationality,
                   AcademicDegree academicDegree) {
        super(pesel, surname, firstName, birthDate, nationality);
        this.academicDegree = academicDegree;
        TeacherExtent.addTeacher(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), academicDegree);
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

        Teacher teacher = (Teacher) obj;

        return Objects.equals(academicDegree, teacher.academicDegree);
    }

    @Override
    public String toString() {
        return super.toString() + " Academic degree: " + academicDegree;
    }

}
