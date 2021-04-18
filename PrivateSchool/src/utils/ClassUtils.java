package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import main.MyMain;

/**
 *
 * @author Maria
 */
public class ClassUtils {

    /**
     * This method get a string from input data, converts it to an integer 
     * and checks if it is a positive integer
     * @return a positive integer
     */
    public int checkInt() {
        int result = 0;
        while (true) {
            String a = MyMain.answer();
            try {
                result = Integer.parseInt(a);
                if (result < 0) {
                    System.out.println("This is a negative number. Please enter a valid number.");
                } else {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
        return result;
    }

    /**
     * This method get a string from input data, converts it to a date 
     * @return a date
     */
    public Date checkDate() {
        LocalDate ld = null;
        while (true) {
            String d = MyMain.answer();
            try {
                ld = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (ld.getDayOfMonth() > 31 || ld.getMonthValue() > 12) {
                } else {
                    break;
                }
            } catch (DateTimeParseException ex) {
                System.out.println("Please enter a valid date in format: \"yyyy-mm-dd\".");
            }
        }
        Date result = Date.valueOf(ld);
        return result;
    }
}
