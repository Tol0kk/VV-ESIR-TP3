package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    private static final int[] daysInMonths = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        // Valid Month
        if (month < 1 ||
                month > 12) {
            return false;
        }

        // Valid Days
        int daysInMonth = daysInMonth(month, year);
        return day >= 1 &&
                day <= daysInMonth;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }

    public Date nextDate() {
        int newDay = this.day;
        int newMonth = this.month;
        int newYear = this.year;

        newDay++;
        if (newDay > daysInMonth(newMonth, newYear)) {
            newDay = 1;
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }

        return new Date(newDay, newMonth, newYear);
    }

    public Date previousDate() {
        int prevDay = this.day;
        int prevMonth = this.month;
        int prevYear = this.year;

        prevDay--;
        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevYear--;
                prevMonth = 12;
            }
            prevDay = daysInMonth(prevMonth, prevYear);
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    // return +1 if this > other
    // return -1 if this < other
    // return 0 if this == other
    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException();
        }

        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }

        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }

        return Integer.compare(this.day, other.day);
    }

    private static int daysInMonth(int month, int year) {
        if (month == 2 &&
         isLeapYear(year)) {
            return 29;
        }
        return daysInMonths[month];
    }

}