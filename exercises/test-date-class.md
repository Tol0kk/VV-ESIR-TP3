# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

# Question 1

## isValidDate(int day, int month, int year)
Characteristics:
    - Valid: 1-31 days based on the month and leap year consideration.
    - Invalid: Out of range values for days, months, or years.
Blocks:
    - Valid dates:
        - (31, 1, 2023) 
        - (29, 2, 2020) // Leap year
        - (1, 1, 0) 
    - Invalid dates: 
        - (32, 1, 10) 
        - (31, 4, 2023) // April has only 30 day
        - (29, 2, 2021) // Invalid for non-leap year
        - (0, 1, 2023) // No 0 day 
        - (1, 0, 2023) // No 0 month 

## isLeapYear(int year)
Characteristics:
     Leap Year occurs each year that is a multiple of 4, except for years evenly divisible by 100 but not by 400. 
    Leap Year / Non-Leap Year
Blocks:
    - Valid leap years:
        - 2020
        - 2000
        - -4 ??
    - Invalid leap years:
        - 2021
        - 1900

## nextDate()
Characteristics:
    Need to be able to compare Date for test (cf `compareTo()`)
Blocks:
    - Transition from month-end to start month
    - Transition from year-end to start year
    - Transition during leap year in febuary
    - Transition from BC to DC era.

## previousDate()
Characteristics:
    Need to be able to compare Date for test
Block:
    Same as `nextDate()`

## CompareTo()
Characteristics:
    ???
Block:
    - Same date
    - previousDate vs current date
    - nextDate vs current date

## Common Characteristics

A lots of method need to know how many days there is in a mouth depending on the month and the year. 

# Question 2

    Forgot to do test the method that could throw

    Add test for:
    - compareTo() (Throws)
    - Date() (Throws)

    Now 100% Code Coverage

# Question 3

I got one case with two boolean operators check in `daysInMonth()`. 

```java
if (month == 2 &&
         isLeapYear(year)) {
            return 29;
        }
```

There is 3 case possible:
- month == 2: false
- month == 2: true & isLeapYear(): true
- month == 2: true & isLeapYear(): false

`daysInMonth()` is private we cannot test it directly in Java. We need to test it indirectly thought each of it's caller (`nextDate()`,`previousDate()`,`isValidDate()`). I will only test it indirectly thought `isValidDate()` since `daysInMonth()` is easy accessible through it.

I added `testIsValidDateDaysInMonth()`.

# Question 4

First run 90% mutant killed.

Add test for:
- getters
- compareTo() (Date Not Equals)
- NextDate() (Transition month 11 to 12)
- PreviousDate() (Transition Day 2 to 1)

Last run 100% mutant killed