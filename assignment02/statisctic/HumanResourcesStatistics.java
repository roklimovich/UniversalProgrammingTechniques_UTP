package pjwstk.edu.pl.s27619.statisctic;

import pjwstk.edu.pl.s27619.employee.Employee;
import pjwstk.edu.pl.s27619.employee.Manager;
import pjwstk.edu.pl.s27619.employee.Trainee;
import pjwstk.edu.pl.s27619.employee.Worker;
import pjwstk.edu.pl.s27619.payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class HumanResourcesStatistics {

    /**
     * Method payrolls for given list of employees
     *
     * @param employees - list which contains information about employees
     * @return list of payroll employees
     */
    public static List<PayrollEntry> payroll(List<Employee> employees) {

        List<PayrollEntry> payrollEntryList = employees.stream()
                .map(PayrollEntry::isWorkerOrNot)
                .collect(Collectors.toList());

        return payrollEntryList;
    }


    /**
     * Method implement payroll for all subordinates of given manager
     *
     * @param manager - contains information about manager
     * @return list of all payroll subordinates
     */
    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        List<PayrollEntry> payrollSubordinatesList = payroll(manager.getAllSubordinates());
        return payrollSubordinatesList;
    }

    /**
     * Method calculate value of all bonuses from the list of employee
     *
     * @param employees - list which contains info about all employees
     * @return total bonus
     */
    public static BigDecimal bonusTotal(List<Employee> employees) {
        BigDecimal totalBonus = employees.stream()
                .filter(w -> w instanceof Worker)
                .map(w -> (Worker) w)
                .map(Worker::getBonus)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalBonus;
    }

    /**
     * Method finds employee who has the longest seniority in company
     *
     * @param employees - list of all employees
     * @return one employee with the longest seniority
     */
    public static Employee longestSeniority(List<Employee> employees) {
        if (employees == null || employees.size() == 0) {
            return null;
        }

        return employees.stream()
                .filter(emp -> emp instanceof Worker)
                .max(Comparator.comparing(w -> ((Worker) w).getWorkingDays()))
                .orElse(null);
    }

    /**
     * Method finds the highest salary of all employees without bonus
     *
     * @param employees - list of all employees
     * @return value of the highest salary without bonus
     */
    public static BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
        BigDecimal highestSalary; // variable which will contain value of the highest salary

        Optional<Employee> highestSalaryOfEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getSalary));

        // if highest salary of employee was founded, we assign this value to highestSalary variable and return, if not
        // we assign highestSalary to zero
        if (highestSalaryOfEmployee.isPresent()) {
            highestSalary = highestSalaryOfEmployee.get().getSalary();
        } else {
            highestSalary = BigDecimal.ZERO;
        }

        return highestSalary;
    }

    /**
     * Method finds the value of the highest salary with bonus
     *
     * @param employees - list of all employees
     * @return value of the highest salary with bonus
     */
    public static BigDecimal highestSalaryIncludingBonus(List<Employee> employees) {
        BigDecimal highestSalaryWithBonus; // variable which will contain value of the highest salary with bonus

        Optional<PayrollEntry> highestSalaryOfEmployeeWithBonus = payroll(employees).stream()
                .max(Comparator.comparing(PayrollEntry::getSalaryPlusBonus));

        // if the highest salary of employee with bonus was founded, we assign this value to highestSalaryWithBonus
        // and return this result, if not we assign highestSalaryWithBonus to zero
        if (highestSalaryOfEmployeeWithBonus.isPresent()) {
            highestSalaryWithBonus = highestSalaryOfEmployeeWithBonus.get().getSalaryPlusBonus();
        } else {
            highestSalaryWithBonus = BigDecimal.ZERO;
        }

        return highestSalaryWithBonus;
    }

    /**
     * Method returns list of employee whose surname begins with A
     *
     * @param manager - contains information about given manager
     * @return list of employee whose surname begins with A
     */
    public static List<Employee> surnameBeginsWithA(Manager manager) {
        // this list contains all subordinates of given manager
        List<Employee> getAllSubordinates = manager.getAllSubordinates();

        // this list contains all subordinates whose surname starts with A
        List<Employee> listOfSubordinatesWithA = getAllSubordinates.stream()
                .filter(emp -> emp.getSurname().startsWith("A"))
                .collect(Collectors.toList());

        return listOfSubordinatesWithA;
    }

    /**
     * Method returns list of employee who earn more than 1000
     *
     * @param employees - list of all employees
     * @return list of employees who earn more than 1000
     */
    public static List<Employee> earnMoreThan1000(List<Employee> employees) {
        // this list contains all employees who earn more than 1000
        List<Employee> employeeList = payroll(employees).stream()
                .filter(payrollEntry -> {
                    BigDecimal salary = payrollEntry.getSalaryPlusBonus();
                    return salary.compareTo(BigDecimal.valueOf(1000)) >= 0;
                })
                .map(PayrollEntry::getEmployee)
                .collect(Collectors.toList());

        return employeeList;
    }


    //ASSIGNMENT 3

    /**
     * Method searches employees older than given employee and earning less than him
     *
     * @param allEmployees list of all employees
     * @param employee     contains info about given employee
     * @return list of employee who older and earn more than given employee
     */
    public static List<Employee> olderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {
        Predicate<Employee> employeeFilter = emp -> emp.isOlder(employee) &&
                emp.isGreater(employee.getSalary());

        List<Employee> result = allEmployees.stream()
                .filter(employeeFilter)
                .collect(Collectors.toList());

        return result;
    }


    /**
     * Method searches trainees whose practise length is longer than given number of days and raise their salary by 5%
     *
     * @param allEmployees list of all employees
     * @param daysCount    variable which contains info about given days
     * @return list of trainees whose practise length longer than some given days
     */
    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
        Predicate<Employee> employeeFilter = emp -> emp instanceof Trainee &&
                ((Trainee) emp).practiseIsLonger(daysCount);

        List<Trainee> listOfTrainees = allEmployees.stream()
                .filter(employeeFilter)
                .map(emp -> {
                    emp.setSalary(emp.getSalary().multiply(new BigDecimal("0.05")).add(emp.getSalary()));
                    return (Trainee) emp;
                }).collect(Collectors.toList());

        return listOfTrainees;
    }

    /**
     * Method searches workers whose seniority is longer than given number of month and give them bonus of 300, if
     * their bonus is smaller
     *
     * @param allEmployees list of all employees
     * @param monthCount   variable which contains info about given month
     * @return list of workers who have seniority longer than given monthCount
     */
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        Predicate<Employee> employeeFilter = emp -> emp instanceof Worker &&
                ((Worker) emp).seniorityIsLongerOfMonth(monthCount);

        List<Worker> workerList = allEmployees.stream()
                .filter(employeeFilter)
                .map(emp -> {
                    ((Worker) emp).giveBonus();
                    return (Worker) emp;
                }).collect(Collectors.toList());

        return workerList;
    }

    /**
     * Method search workers whose seniority is between 1 and 3 years, and give them raise of salary by 10%
     *
     * @param allEmployees list of all employees
     * @return list of worker whose seniority between one and three years
     */
    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        Predicate<Employee> employeeFilter = emp -> emp instanceof Worker &&
                ((Worker) emp).seniorityIsLongerOfYears(1) && !((Worker) emp).seniorityIsLongerOfYears(3);

        List<Worker> workerList = allEmployees.stream()
                .filter(employeeFilter)
                .map(emp -> {
                    emp.setSalary(emp.getSalary().multiply(new BigDecimal("0.1")).add(emp.getSalary()));
                    return (Worker) emp;
                }).collect(Collectors.toList());

        return workerList;
    }

    /**
     * Method searches workers whose seniority is longer than the seniority of a given employee and earn less than him
     * and align their salary with the given employee
     *
     * @param allEmployees list of all employees
     * @param employee     given employee
     * @return list of workers whose seniority is longer than seniority of given employee
     */
    public static List<Worker> seniorityLongerAndEarnLessThan(List<Employee> allEmployees, Employee employee) {
        Predicate<Employee> employeeFilter = emp -> emp instanceof Worker &&
                ((Worker) emp).seniorityIsLongerOfYears(((Worker) employee).getWorkingYears()) &&
                emp.isLess(employee.getSalary());

        List<Worker> workerList = allEmployees.stream()
                .filter(employeeFilter)
                .map(emp -> {
                    emp.setSalary(employee.getSalary());
                    return (Worker) emp;
                }).collect(Collectors.toList());

        return workerList;
    }


    /**
     * Method searches workers whose seniority is between 2 and 4 years and whose age is greater than given number of
     * years
     *
     * @param allEmployees list of all employees
     * @param age          contains info about given age
     * @return list of workers whose seniority is between two and four years and age greater than given age
     */
    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        Predicate<Employee> employeeFilter = emp -> emp instanceof Worker &&
                ((Worker) emp).seniorityIsLongerOfYears(2) && !((Worker) emp).seniorityIsLongerOfYears(4) &&
                emp.getAge() > age;

        List<Worker> workerList = allEmployees.stream()
                .filter(employeeFilter)
                .map(emp -> (Worker) emp)
                .collect(Collectors.toList());

        return workerList;
    }


    /**
     * samples for functional processing in Java
     */
    public static List<Short> getAges(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        List<Short> ages = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .collect(Collectors.toList());
        return ages;
    }

    public static void printAges(List<Employee> employees) {
        if (employees == null) {
            return;
        }
        employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .forEach(age -> System.out.print(age + ", "));
    }

    //
    // average age for the Employees whose first name starts with 'A' and they are older than 20
    public static short getAverageAgeInline(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, //
                        (total, age) -> total + age);

        long filteredEmployeesCount = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .count();

        return (short) (employeeTotalAge / filteredEmployeesCount);
    }

    public static short getAverageAgeMethodReference(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, HumanResourcesStatistics::totalAge);
        return (short) (employeeTotalAge / employees.size());
    }

    public static short getMaxAgeInline(List<Employee> employees) {
        short employeeMaxAge = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .reduce((short) 0, //
                        (maxAge, age) -> {
                            if (maxAge < age) {
                                return age;
                            } else {
                                return maxAge;
                            }
                        });
        return employeeMaxAge;
    }

    public static short getMaxAgeMethodReference(List<Employee> employees) {
        short employeeMaxAge = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .reduce((short) 0, HumanResourcesStatistics::maxAge);
        return employeeMaxAge;
    }

    private static int totalAge(int totalAge, int age) {
        //
        return totalAge + age;
    }

    private static short maxAge(short maxAge, short age) {
        if (maxAge < age) {
            return age;
        } else {
            return maxAge;
        }
    }

}
