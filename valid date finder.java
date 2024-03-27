import java.util.regex.*;
import java.util.*;

public class DateExtractor {
    public static void main(String[] args) {
        String text = "Hdjsh asd2324234jghjsd hjsdg sdhk 12212021 idf32432 32423 d34234jh dfh";
        List<String> dates = extractDates(text);
        
        System.out.println("Valid dates found:");
        for (String date : dates) {
            System.out.println(date);
        }
    }
    
    public static List<String> extractDates(String text) {
        List<String> validDates = new ArrayList<>();
        String regex = "(0?[1-9]|1[0-2])(0?[1-9]|[12][0-9]|3[01])(19|20)\\d\\d"; // MMDDYYYY
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        while (matcher.find()) {
            String date = matcher.group();
            if (isValidDate(date)) {
                validDates.add(date);
            }
        }
        
        return validDates;
    }
    
    public static boolean isValidDate(String date) {
        String[] parts = {date.substring(0, 2), date.substring(2, 4), date.substring(4)};
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        
        if (month < 1 || month > 12) {
            return false;
        }
        
        if (day < 1 || day > 31) {
            return false;
        }
        
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }
        
        if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                if (day > 29) {
                    return false;
                }
            } else {
                if (day > 28) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
