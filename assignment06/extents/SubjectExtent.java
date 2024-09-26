package pjwstk.edu.pl.s27619.source.extents;

import pjwstk.edu.pl.s27619.source.management.Subject;

import java.util.*;

public class SubjectExtent {
    private static Set<Subject> subjectSet = new HashSet<>();
    private static List<Subject> subjectList = new ArrayList<>();

    /**
     * Method adds subject to set and list of all subjects
     *
     * @param subject given object which should be added
     */
    public static void addSubject(Subject subject) {
        subjectSet.add(subject);
        subjectList.add(subject);
        subjectList.sort(Comparator.naturalOrder());
    }

}
