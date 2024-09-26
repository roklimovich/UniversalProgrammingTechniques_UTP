package pjwstk.edu.pl.s27619.payroll;

import pjwstk.edu.pl.s27619.employee.Employee;
import pjwstk.edu.pl.s27619.employee.Worker;

import java.math.BigDecimal;

public final class PayrollEntry {

    private final Employee employee;
    private final BigDecimal salaryPlusBonus;

    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        this.employee = employee;
        // validate whether salary and bonus are not null
        if (salary != null && bonus != null) {
            salaryPlusBonus = salary.add(bonus);
        } else if (salary == null) {
            salaryPlusBonus = bonus;
        } else {
            salaryPlusBonus = salary;
        }
    }

    /**
     * Method checks if the given employee is worker or not
     *
     * @param employee - contains information about given employee
     * @return payroll entry
     */
    public static PayrollEntry isWorkerOrNot(Employee employee) {
        PayrollEntry payrollEntry; // this variable contains info about employee, his salary, and his bonus

        if (employee instanceof Worker) {
            payrollEntry = new PayrollEntry(employee, employee.getSalary(), ((Worker) employee).getBonus());
        } else {
            payrollEntry = new PayrollEntry(employee, employee.getSalary(), BigDecimal.ZERO);
        }

        return payrollEntry;
    }

    public Employee getEmployee() {
        return employee;
    }

    public BigDecimal getSalaryPlusBonus() {
        return salaryPlusBonus;
    }

}