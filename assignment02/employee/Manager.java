package pjwstk.edu.pl.s27619.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {

    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)
    private List<Employee> subordinates;

    public Manager(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
                   LocalDate employmentDate, BigDecimal bonus, List<Employee> subordinates) {
        super(firstName, surname, birthDate, salary, manager, employmentDate, bonus);
        this.subordinates = subordinates;

    }

    /**
     * Method returns list of all subordinates
     *
     * @return all subordinates
     */
    public List<Employee> getAllSubordinates() {
        List<Employee> allSubordinates = new ArrayList<>();
        if (subordinates == null) {
            return allSubordinates;
        }

        allSubordinates.addAll(subordinates);

        for (Employee subordinate : subordinates) {
            if (subordinate instanceof Manager) {
                allSubordinates.addAll(((Manager) subordinate).getAllSubordinates());
            }
        }
        return allSubordinates;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

}