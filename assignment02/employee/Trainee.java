package pjwstk.edu.pl.s27619.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Trainee extends Employee {

    // attributes:
    // * apprenticeship start date
    // * apprenticeship length (in days)

    private LocalDate teachingStart;

    public Trainee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
                   LocalDate teachingStart) {
        //
        super(firstName, surname, birthDate, salary, manager);

        this.teachingStart = teachingStart;
    }

    public int getTeachingLength() {
        return Period.between(teachingStart, LocalDate.now()).getDays();
    }

    public LocalDate getTeachingStart() {
        return teachingStart;
    }

    public void setTeachingStart(LocalDate teachingStart) {
        this.teachingStart = teachingStart;
    }

    // ASSIGNMENT 3

    /**
     * Method which returns boolean result of comparing given days and practise length
     *
     * @param days contains info about given days
     * @return true if shorter, and false if not
     */
    public boolean practiseIsShorter(int days) {
        int getPractiseLength = getTeachingLength();
        return getPractiseLength < days;
    }

    /**
     * Method which returns boolean result of comparing given days and practise length
     *
     * @param days contains info about given days
     * @return true if longer, and false if not
     */
    public boolean practiseIsLonger(int days) {
        int getPractiseLength = getTeachingLength();
        return getPractiseLength > days;
    }
}