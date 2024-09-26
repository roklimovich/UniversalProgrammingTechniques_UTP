package pjwstk.edu.pl.s27619.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SearchExtensionForZip {

    /**
     * Method searches ZipEntry in directory with the special name
     *
     * @param zipFile is the ZipFile which should be searched
     * @param name    string variable which contains name of ZipEntry
     * @return list of ZipEntries
     */
    public static List<ZipEntry> searchFileInDirectory(ZipFile zipFile, String name) {
        // Predicate to check if the ZipEntry name ends with the special name
        Predicate<ZipEntry> zipEntryPredicate = zipEntry -> zipEntry.getName().endsWith(name);

        // // List of type ZipEntry, which contains all necessary ZipEntries
        List<ZipEntry> zipEntryList = zipFile.stream()
                .filter(zipEntryPredicate)
                .collect(Collectors.toList());

        return zipEntryList;
    }

    /**
     * Method searches ZipEntries with special content
     *
     * @param zipFile is the ZipFile which should be searched
     * @param content string variable which contains content of file
     * @return list of ZipEntries
     */
    public static List<ZipEntry> searchContent(ZipFile zipFile, String content) {
        // Predicate to check if the ZipEntry is a file
        Predicate<ZipEntry> zipEntryPredicate = zipEntry -> !zipEntry.isDirectory();
        List<ZipEntry> zipEntryList = new ArrayList<>();

        // Iterate all entries in the zipFile
        zipFile.stream()
                .filter(zipEntryPredicate)
                .forEach(currFile -> {
                    try {
                        // Read using scanner current file and if special content was found add to list of entries
                        if (new Scanner(zipFile.getInputStream(currFile))
                                .findWithinHorizon(content, 0) != null) {
                            zipEntryList.add(currFile);
                        }
                    } catch (IOException e) {
                        System.out.println("Exception in method searchContent in ZIP was thrown");
                    }
                });

        return zipEntryList;
    }

    /**
     * Method searches ZipEntry in directory with the special name by parallel
     *
     * @param zipFile is the zipFile which should be searched
     * @param name    string variable which contains name of JarEntry
     * @return list of ZipEntries
     */
    public static List<ZipEntry> searchFileParallelInDirectory(ZipFile zipFile, String name) {
        // Predicate to check if the ZipEntry name ends with the special name
        Predicate<ZipEntry> zipEntryPredicate = zipEntry -> zipEntry.getName().endsWith(name);

        // List of type ZipEntry, which contains all necessary ZipEntries by parallel
        List<ZipEntry> zipEntryList = zipFile.stream()
                .parallel()
                .filter(zipEntryPredicate)
                .collect(Collectors.toList());

        return zipEntryList;
    }

}
