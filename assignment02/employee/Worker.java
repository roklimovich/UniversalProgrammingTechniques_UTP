package pjwstk.edu.pl.s27619.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    private LocalDate employmentDate;
    private BigDecimal bonus;
    private boolean hasBonus = false;


    public Worker(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
                  LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surname, birthDate, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public int getWorkingDays() {
        return Period.between(employmentDate, LocalDate.now()).getDays();
    }


    //ASSIGNMENT 3

    /**
     * Method returns boolean result of comparing seniority in years of given worker with number of years
     *
     * @param years variable which contains info about given years
     * @return true, if longer, and false is not
     */
    public boolean seniorityIsLongerOfYears(int years) {
        return employmentDate.compareTo(LocalDate.now().minusYears(years)) < 0;
    }

    /**
     * Method returns boolean result of comparing seniority in month of given worker with number of month
     *
     * @param month variable which contains info about given month
     * @return true, if longer, and false if not
     */
    public boolean seniorityIsLongerOfMonth(int month) {
        return employmentDate.compareTo(LocalDate.now().minusMonths(month)) < 0;
    }

    /**
     * Method returns boolean result of comparing bonus and given amount of money
     *
     * @param money variable which contains amount of money
     * @return true if bonus greater than amount of money, and false, if not
     */
    public boolean hasBonusGreaterThanMoney(BigDecimal money) {
        return hasBonus && bonus.compareTo(money) > 0;
    }

    /**
     * Method gives amount of money to worker
     */
    public void giveBonus() {
        // check if worker has bonus that greater than 300, and if not add 300 to his bonus
        if (!hasBonusGreaterThanMoney(new BigDecimal(300))) {
            bonus = bonus.add(new BigDecimal(300));
        }
    }

    /**
     * Method returns result of comparing time between now and employment date in years
     *
     * @return int variable which contains amount of working years
     */
    public int getWorkingYears() {
        return Period.between(LocalDate.now(), employmentDate).getYears();
    }

}