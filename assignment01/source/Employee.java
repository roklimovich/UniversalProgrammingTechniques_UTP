package pjwstk.edu.pl.s27619.assignmnet01.source;

import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IAggregable;
import pjwstk.edu.pl.s27619.assignmnet01.interfaces.IDeeplyCloneable;

public class Employee implements IAggregable<Employee, Integer>, IDeeplyCloneable<Employee> {

    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * This method finds sum of salary and intermediate result
     *
     * @param intermediateResult - contains information about intermediate result of aggregating
     * @return sum of two values
     */
    @Override
    public Integer aggregate(Integer intermediateResult) {

        //if intermediate result equals null, we just return salary without any manipulations
        if (intermediateResult == null) {
            return salary;
        }

        return salary + intermediateResult;
    }

    /**
     * Method clones current object
     *
     * @return cloned object of class Employee
     */
    @Override
    public Employee deepClone() {
        return new Employee(name, salary);
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }
}
