package pjwstk.edu.pl.s27619.source.extents;

import pjwstk.edu.pl.s27619.source.management.StudentGroup;

import java.util.*;

public class StudentGroupExtent {
    private static Set<StudentGroup> studentGroupSet = new HashSet<>();
    private static List<StudentGroup> studentGroupList = new ArrayList<>();

    /**
     * Method adds student group to set and list of all student groups
     *
     * @param studentGroup given object which should be added
     */
    public static void addStudentGroup(StudentGroup studentGroup) {
        studentGroupSet.add(studentGroup);
        studentGroupList.add(studentGroup);
        studentGroupList.sort(Comparator.naturalOrder());
    }

}
