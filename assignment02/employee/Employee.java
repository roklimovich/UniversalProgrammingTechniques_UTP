package pjwstk.edu.pl.s27619.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    private Manager manager;
    private BigDecimal salary;

    protected Employee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager) {
        super(firstName, surname, birthDate);
        this.salary = salary;
        this.manager = manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return manager;
    }

    public BigDecimal getSalary() {
        return salary;
    }


    // ASSIGNMENT 3

    /**
     * Method compare salary with given amount of money
     *
     * @param money given amount of money
     * @return true if greater, and false, if not
     */
    public boolean isGreater(BigDecimal money) {
        return salary.compareTo(money) > 0;
    }

    /**
     * Method compare salary with given amount of money
     *
     * @param money given amount of money
     * @return true if less, and false, if not
     */
    public boolean isLess(BigDecimal money) {
        return salary.compareTo(money) < 0;
    }

    /**
     * Method compare salary with other employee salary
     *
     * @param employee given employee
     * @return true if greater, and false if not
     */
    public int compareSalary(Employee employee) {
        return salary.compareTo(employee.getSalary());
    }

}
