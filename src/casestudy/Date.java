package casestudy;

import java.util.Calendar;

public class Date {
    public static boolean isBetween(Calendar theDate, Calendar startDate, Calendar endDate) {
        return (theDate.compareTo(startDate) >= 0) && (theDate.compareTo(endDate) <= 0);
    }
}
