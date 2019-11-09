package casestudy;

import java.time.LocalDate;

public class Date {
    public static boolean isBetween(LocalDate theDate, LocalDate startDate, LocalDate endDate) {
        return (!theDate.isBefore(startDate)) && (!theDate.isAfter(endDate));
    }
}
