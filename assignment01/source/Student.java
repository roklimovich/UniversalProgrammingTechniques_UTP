package pjwstk.edu.pl.s27619.assignmnet01.source;

import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IAggregable;
import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IDeeplyCloneable;

public class Student implements IAggregable<Student, Double>, IDeeplyCloneable<Student> {

    private String studentId;
    private double grade;

    public Student(String studentId, double grade) {
        this.studentId = studentId;
        this.grade = grade;
    }

    /**
     * This method finds maximum between grade and intermediate result
     *
     * @param intermediateResult - contains information about intermediate result of aggregating
     * @return maximum from two values
     */
    @Override
    public Double aggregate(Double intermediateResult) {

        //if intermediate result equals null, we just return grade without any manipulations
        if (intermediateResult == null) {
            return grade;
        }

        return Math.max(grade, intermediateResult);
    }

    /**
     * Method clones current object
     *
     * @return cloned object of class Student
     */
    @Override
    public Student deepClone() {
        return new Student(studentId, grade);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getGrade() {
        return grade;
    }
}
