package pjwstk.edu.pl.s27619.source.people;

import java.util.Random;

public enum AcademicDegree {
    DOCTORATE,
    MASTER,
    BACHELOR;

    /**
     * Method generates random academic degree for teacher
     *
     * @return object of type AcademicDegree
     */
    public static AcademicDegree generateAcademicDegree() {
        Random random = new Random();
        AcademicDegree[] academicDegrees = AcademicDegree.values();
        return academicDegrees[random.nextInt(academicDegrees.length)];
    }

}
