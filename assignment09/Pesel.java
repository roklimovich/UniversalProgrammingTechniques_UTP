package pjwstk.edu.pl.s27619.source;

import java.time.LocalDate;

public class Pesel {
    private String pesel;
    private static final int VALID_PESEL_LENGTH = 11;
    private static final int PESEL_LENGTH_WITHOUT_CONTROL_DIGIT = 10;
    private static final int PESEL_CHECKER_INDEX = 10;
    private static final int PESEL_SEX_INDEX = 9;
    private static final int PESEL_ID_BEGIN_INDEX = 6;
    private static final int PESEL_ID_END_INDEX = 10;
    private static final int PESEL_YEAR_BEGIN_INDEX = 0;
    private static final int PESEL_YEAR_END_INDEX = 2;
    private static final int PESEL_MONTH_BEGIN_INDEX = 2;
    private static final int PESEL_MONTH_END_INDEX = 4;
    private static final int PESEL_DAY_BEGIN_INDEX = 4;
    private static final int PESEL_DAY_END_INDEX = 6;
    private static final int[] SPECIAL_SEQ = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    public Pesel(String pesel) throws AssignmentException {
        if (!isValidPeselOrNot(pesel)) {
            throw new AssignmentException("INVALID PESEL");
        } else {
            this.pesel = pesel;
        }

    }

    /**
     * Method check if the entered PESEL is valid or not
     *
     * @param pesel contains contains numbers of PESEL, which should be checked
     * @return true, if PESEL is valid, and false if isn't valid
     */
    private static boolean isValidPeselOrNot(String pesel) {
        int sum = 0; // Variable which contains info about sum of digits in entered PESEL
        int controlDigitChecker; // Variable which contains info about current control digit
        int controlDigit; // Variable contains info about control digit in entered PESEL
        char[] peselToChars = pesel.toCharArray(); // PESEL in char array

        // If length of pesel isn't equals to valid pesel length, we just return false
        if (peselToChars.length != VALID_PESEL_LENGTH) {
            return false;
        }

        // Iterate all chars from char array without control digit and multiply each value with the value of
        // special sequence with the same index, then add this value to our sum variable. If something go wrong
        // throw NumberFormatException and show to the user special message, and return false
        for (int i = 0; i < PESEL_LENGTH_WITHOUT_CONTROL_DIGIT; i++) {
            try {
                sum += Integer.parseInt(peselToChars[i] + "") * SPECIAL_SEQ[i];

            } catch (NumberFormatException e) {
                System.out.println("Can't parse char to int");
                return false;
            }
        }

        // Try to parse control digit in pesel from char to int and if something go wrong, throw NumberFormatException,
        // show special message to the user and return false
        try {
            controlDigit = Integer.parseInt(pesel.substring(PESEL_CHECKER_INDEX));
        } catch (NumberFormatException e) {
            System.out.println("Can't parse digit checker to int");
            return false;
        }

        // Calculate the control digit value according to our calculated sum
        int lastDigit = sum % 10;

        // Check if last digit equals zero, then the control digit checker will be also 0, else we should calculate
        // the control digit checker by subtracting 10 from the given last digit
        if (lastDigit == 0) {
            controlDigitChecker = 0;
        } else {
            controlDigitChecker = 10 - lastDigit;
        }

        // If control digit check is equals to control digit, then PESEL is valid and return true, else PESEL is not
        // valid and we return false
        if (controlDigitChecker == controlDigit) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method extract person sex from entered PESEL by the user
     *
     * @return if digitSex is odd, returns female, and if digitSex is even, returns male
     */
    private PersonSex extractPersonSex() {
        // Get the number from PESEL from index with number 9
        int digitSex = Integer.parseInt(pesel.charAt(PESEL_SEX_INDEX) + "");

        // Check if number is odd, return female, if digitSex is even, then return male
        if ((digitSex % 2) == 0) {
            return PersonSex.FEMALE;
        } else {
            return PersonSex.MALE;
        }
    }

    /**
     * Method extracts person id from entered PESEL by the user
     *
     * @return object of type String from given range of indexes
     */
    private String extractPersonId() {
        return pesel.substring(PESEL_ID_BEGIN_INDEX, PESEL_ID_END_INDEX);
    }

    /**
     * Method to extracts date from entered PESEL by the user
     *
     * @return object of type LocalDate with valid date from PESEL
     */
    private LocalDate extractDate() {
        String year = pesel.substring(PESEL_YEAR_BEGIN_INDEX, PESEL_YEAR_END_INDEX);
        String monthInText = pesel.substring(PESEL_MONTH_BEGIN_INDEX, PESEL_MONTH_END_INDEX);
        String day = pesel.substring(PESEL_DAY_BEGIN_INDEX, PESEL_DAY_END_INDEX);

        int firstPartOfYear; // Variable contains century of date
        int month = Integer.parseInt(monthInText); // Variable contains numerical value of monthInText

        if ((month - 80) > 0) {
            firstPartOfYear = 19;
            month -= 80;
        } else if ((month - 60) > 0) {
            firstPartOfYear = 23;
            month -= 60;
        } else if ((month - 40) > 0) {
            firstPartOfYear = 22;
            month -= 40;
        } else if ((month - 20 > 0)) {
            firstPartOfYear = 21;
            month -= 20;
        } else {
            firstPartOfYear = 20;
        }

        // Subtracting one from century to find the first two values of the year
        year = (firstPartOfYear - 1) + year;

        return LocalDate.parse(year + String.format("-%02d-", month) + day);
    }

    @Override
    public String toString() {
        return "Pesel: " + pesel + ", isValid: " + isValidPeselOrNot(pesel) + ", extractBirthDate: " + extractDate()
                + ", extractID: " + extractPersonId() + ", extractPersonSex: " + extractPersonSex();
    }
}
