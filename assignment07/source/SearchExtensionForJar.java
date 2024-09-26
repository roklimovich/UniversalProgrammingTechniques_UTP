package pjwstk.edu.pl.s27619.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class SearchExtensionForJar {

    /**
     * Method searches JarEntry in directory with the special name
     *
     * @param jarFile is the jarfile which should be searched
     * @param name    string variable which contains name of JarEntry
     * @return list of JarEntries
     */
    public static List<JarEntry> searchFileInDirectory(JarFile jarFile, String name) {
        // Predicate to check if the JarEntry name ends with the special name
        Predicate<JarEntry> jarEntryPredicate = jarEntry -> jarEntry.getName().endsWith(name);

        // List of type JarEntry, which contains all necessary JarEntries
        List<JarEntry> jarEntryList = jarFile.stream()
                .filter(jarEntryPredicate)
                .collect(Collectors.toList());

        return jarEntryList;
    }

    /**
     * Method searches JarEntries with special content
     *
     * @param jarFile is the jarfile which should be searched
     * @param content string variable which contains content of file
     * @return list of JarEntries
     */
    public static List<JarEntry> searchContent(JarFile jarFile, String content) {
        // Predicate to check if the JarEntry is a file
        Predicate<JarEntry> jarEntryPredicate = jarEntry -> !jarEntry.isDirectory();
        List<JarEntry> jarEntryList = new ArrayList<>();

        // Iterate all entries in the JarFile
        jarFile.stream()
                .filter(jarEntryPredicate)
                .forEach(currFile -> {
                    try {
                        // Read using scanner current file and if special content was found add to list of entries
                        if (new Scanner(jarFile.getInputStream(currFile))
                                .findWithinHorizon(content, 0) != null) {
                            jarEntryList.add(currFile);
                        }
                    } catch (IOException e) {
                        System.out.println("Exception in method searchContent in JAR was thrown");
                    }
                });

        return jarEntryList;
    }

    /**
     * Method searches JarEntry in directory with the special name by parallel
     *
     * @param jarFile is the jarfile which should be searched
     * @param name    string variable which contains name of JarEntry
     * @return list of JarEntries
     */
    public static List<JarEntry> searchFileParallelInDirectory(JarFile jarFile, String name) {
        // Predicate to check if the JarEntry name ends with the special name
        Predicate<JarEntry> jarEntryPredicate = jarEntry -> jarEntry.getName().endsWith(name);

        // List of type JarEntry, which contains all necessary JarEntries by parallel
        List<JarEntry> jarEntryList = jarFile.stream()
                .parallel()
                .filter(jarEntryPredicate)
                .collect(Collectors.toList());

        return jarEntryList;
    }


}
