package pjwstk.edu.pl.s27619.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchExtensionForFiles {

    /**
     * Method searches files in directory with names which ending to special name as parameter of the method
     *
     * @param file it is directory to start the search
     * @param name variable of type String which contains special name
     * @return list of type File
     */
    public static List<File> searchFileInDirectory(File file, String name) {
        // Predicate to check if Path name ends with special name
        Predicate<Path> pathPredicate = path -> path.endsWith(name);

        List<File> fileList = new ArrayList<>();

        try {
            fileList = Files.walk(file.toPath())
                    .filter(pathPredicate)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Exception in method searchFileInDirectory was thrown");
        }

        return fileList;
    }

    /**
     * Method searches files in directory with names which ending to special name as parameter of the method
     * by parallel
     *
     * @param file it is directory to start the search
     * @param name variable of type String which contains special name
     * @return list of type File
     */
    public static List<File> searchFileParallelInDirectory(File file, String name) {
        // Predicate to check if Path name ends with special name
        Predicate<Path> pathPredicate = path -> path.endsWith(name);

        List<File> fileList = new ArrayList<>();

        try {
            fileList = Files.walk(file.toPath())
                    .parallel()
                    .filter(pathPredicate)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Exception in method searchFileParallelInDirectory was thrown");
        }

        return fileList;
    }

    /**
     * Method searches files in directory with special content which is defined as parameter of the method
     *
     * @param file    it is directory to start the search
     * @param content variable of type String which contains special content to search
     * @return list of type File
     */
    public static List<File> searchFileByContent(File file, String content) {
        List<File> fileList = new ArrayList<>();

        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(file.getPath()),
                    path -> path.toFile().isFile());
            for (Path path : directoryStream) {
                File currFile = path.toFile();

                // Check if special content was found, add to list of our files, if can't reading file, IOException
                // will be thrown
                try {
                    Scanner scanner = new Scanner(currFile);
                    if (scanner.findWithinHorizon(content, 0) != null) {
                        fileList.add(file);
                    }
                } catch (IOException e) {
                    System.out.println("Exception in reading file was thrown");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception in searchFileByContent was thrown");
        }

        return fileList;
    }

}
