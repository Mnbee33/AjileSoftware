package casestudy;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date extends GregorianCalendar {
    public static boolean isBetween(Calendar theDate, Calendar startDate, Calendar endDate) {
        return (theDate.compareTo(startDate) >= 0) && (theDate.compareTo(endDate) <= 0);
    }
}
