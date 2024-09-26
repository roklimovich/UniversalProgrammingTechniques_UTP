package pjwstk.edu.pl.s27619.source.extents;

import pjwstk.edu.pl.s27619.source.management.Department;

import java.util.*;

public class DepartmentExtent {
    private static Set<Department> departmentSet = new HashSet<>();
    private static List<Department> departmentList = new ArrayList<>();

    /**
     * Method adds department to set and list of all departments
     *
     * @param department given object which should be added
     */
    public static void addDepartment(Department department) {
        departmentSet.add(department);
        departmentList.add(department);
        departmentList.sort(Comparator.naturalOrder());
    }

    /**
     * Method returns random department from the list of all departments
     *
     * @return object of type Department
     */
    public static Department getRandomDepartment() {
        return departmentList.get(new Random().nextInt(departmentList.size()));
    }
}
