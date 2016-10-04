package ca.mahram.android.androidjava8.model;

import android.support.annotation.NonNull;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Date;

/**
 Created by mahramf on 2016-10-03.
 */
public class Person {
    public final @NonNull String firstName;
    public final @NonNull String lastName;
    public final @NonNull Date   birthdayUTC;

    public Person (final String fn, final String ln, final Date bd) {
        firstName = fn;
        lastName = ln;
        birthdayUTC = bd;
    }

    public String fullName () {
        return String.format ("%s %s", firstName, lastName);
    }

    public int age () {
        final Period lifetime = new Period (birthdayUTC.getTime () - new Date ().getTime (), PeriodType.years ());
        return lifetime.getYears ();
    }

    public static int compareByAge (final Person a, final Person b) {
        return a.age () - b.age ();
    }

    public static int compareByFirstName (final Person a, final Person b) {
        return a.firstName.compareToIgnoreCase (b.firstName);
    }

    public static int compareByLastName (final Person a, final Person b) {
        return a.lastName.compareToIgnoreCase (b.lastName);
    }

    public static int compareByFullName (final Person a, final Person b) {
        return a.fullName ().compareToIgnoreCase (b.fullName ());
    }
}
