package pjwstk.edu.pl.s27619.source.extents;

import pjwstk.edu.pl.s27619.source.people.Teacher;

import java.util.*;

public class TeacherExtent {
    private static Set<Teacher> teacherSet = new HashSet<>();
    private static List<Teacher> teacherList = new ArrayList<>();

    /**
     * Method adds teacher to set and list of all teachers
     *
     * @param teacher given object which should be added
     */
    public static void addTeacher(Teacher teacher) {
        teacherSet.add(teacher);
        teacherList.add(teacher);
        teacherList.sort(Comparator.naturalOrder());
    }

    /**
     * Method returns random teacher from the list of all teachers
     *
     * @return random object of type Teacher from list
     */
    public static Teacher getRandomTeacher() {

        return teacherList.get(new Random().nextInt(teacherList.size()));
    }

}
