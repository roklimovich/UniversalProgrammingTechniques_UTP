package pjwstk.edu.pl.s27619.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public final class InputParser {

    // 1. Use regular expresssions (Pattern) for validating input data
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String pattern = "\\w+\\s\\w+\\s\\[0-9]{4}\\-[0-1][0-9]\\-[0-3][0-9]";


    /**
     * Method parse information from file to list of persons
     *
     * @param file contains file name, which needed to parse
     * @return list of persons with information from file
     * @throws IOException if program can't parse the file
     */
    public static List<Person> parse(File file) throws IOException {
        List<Person> personList = new ArrayList<>(); // Contains info about persons in list

        // Using try-catch block trying to read file, if something will go wrong, we throw IOException, and show special
        // message to the user
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String inputLine;

            // Get the information from file in simple text, while file will be not empty
            while ((inputLine = bufferedReader.readLine()) != null) {

                if (!checkIsCorrectFormat(inputLine)) {

                    String[] textArray = inputLine.split(" "); // Divide giving line to array with text

                    // Check if array meet the requirements of given pattern
                    if (textArray.length < 3) {
                        continue;
                    }

                    String firstName = textArray[0]; // Contains first name from text array
                    String surname = textArray[1]; // Contains surname from text array
                    Date birthdate = null; // Contains info about person's birthdate

                    // Using try-catch block trying to parse given date from text array to needed format, if something
                    // is going wrong, throws exception and show special message to the user
                    try {
                        birthdate = dateFormat.parse(textArray[2]);
                    } catch (ParseException e) {
                        System.out.println("Can't parse string to date");
                    }

                    personList.add(new Person(firstName, surname, birthdate));

                } else {
                    System.out.println("Incorrect format");
                }
            }

        } catch (IOException e) {
            System.out.println("Can't read the file");
        }

        return personList;
    }

    /**
     * Method compare input line of type string with our specific pattern
     *
     * @param inputLine type of string, which contains text from line in a file
     * @return true, if it is correct, and false, if not
     */
    public static boolean checkIsCorrectFormat(String inputLine) {

        return Pattern.matches(inputLine, pattern);
    }
}
