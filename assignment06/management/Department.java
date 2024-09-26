package pjwstk.edu.pl.s27619.source.management;

import pjwstk.edu.pl.s27619.source.extents.DepartmentExtent;
import pjwstk.edu.pl.s27619.source.people.Teacher;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Department implements Comparable<Department> {
    private String name;
    private List<Teacher> employees;

    public Department(String name, List<Teacher> employees){
        this.name = name;
        this.employees = employees;
        DepartmentExtent.addDepartment(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Department department = (Department) obj;
        return Objects.equals(name, department.name) && Objects.equals(employees, department.employees);
    }

    @Override
    public int compareTo(Department anotherDepartment) {
        return Comparator.comparing(Department::getName).compare(this, anotherDepartment);
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Department name: " + name;
    }
}
