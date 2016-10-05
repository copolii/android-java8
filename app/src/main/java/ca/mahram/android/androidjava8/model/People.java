package ca.mahram.android.androidjava8.model;

import android.annotation.SuppressLint;
import android.util.SparseArray;

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.mahram.android.androidjava8.annotations.Photo;

/**
 Created by mahramf on 2016-10-03.
 */

@Photo (value = "http://media.boingboing.net/wp-content/uploads/2015/06/WR-7.jpg", title = "Winona Ryder")
@Photo (value = "https://s3-eu-central-1.amazonaws" +
        ".com/centaur-wp/creativereview/prod/content/uploads/2016/09/StrangerThingsstill5.jpg", title = "Joyce")
@Photo (value = "http://i1.mirror.co.uk/incoming/article8513820" +
        ".ece/ALTERNATES/s615b/Millie-Bobby-Brown-and-Winona-Ryder-from-Netflixs-Stranger-Things.jpg", title = "Winona & Millie")
@Photo (value = "http://media.boingboing.net/wp-content/uploads/2016/09/owen0715-stranger-things.jpg", title = "Get off my lawn!")
@Photo (value = "http://images.hellogiggles.com/uploads/2016/08/05064304/dustin.jpg", title = "Dustin")
@Photo (value = "http://www.thewrap.com/wp-content/uploads/2016/08/millie-bobby-brown-stranger-things.png", title = "Millie Bobby Brown")
@Photo ("https://i.ytimg.com/vi/Dos0pjBT1ig/maxresdefault.jpg")
public final class People {
    // cast of Stranger things from IMDB some Birthdays are fake because they were missing

    public static final Person[] PEOPLE = new Person[] {
      new Person ("Winona", "Ryder", new Date (56933445000L)),
      new Person ("David", "Harbour", new Date (166320000000L)),
      new Person ("Finn", "Wolfhard", new Date (1023840000000L)),
      new Person ("Millie Bobby", "Brown", new Date (1077148800000L)),
      new Person ("Gaten", "Matarazzo", new Date (1031443200000L)),
      new Person ("Caleb", "McLaughlin", new Date (1042416000000L)),
      new Person ("Natalia", "Dyer", new Date (827107200000L)),
      new Person ("Charlie", "Heaton", new Date (784598400000L)),
      new Person ("Cara", "Buono", new Date (36633600000L)),
      new Person ("Matthew", "Modine", new Date (-340243200000L)),
      new Person ("Joe", "Keery", new Date (819331200000L)),
      new Person ("Rob", "Morgan", new Date (-532137600000L)),
      new Person ("John", "Reynolds", new Date (265939200000L)),
      new Person ("Joe", "Chrest", new Date (-51753600000L)),
      new Person ("Noah", "Schnapp", new Date (1053216000000L)),
      new Person ("Mark", "Steger", new Date (-251164800000L)),
      new Person ("Randall P.", "Havens", new Date (-218160000000L)),
      new Person ("Tobias", "Jelinek", new Date (148176000000L)),
      new Person ("Susan", "Shalhoub Larkin", new Date (69465600000L))
    };

    private static final SparseArray<Person> map;

    static {
        map = new SparseArray<> ();

        for (final Person p : PEOPLE)
            map.put (p.id, p);
    }

    @SuppressLint ("SimpleDateFormat")
    private static final SimpleDateFormat BD_FORMAT = new SimpleDateFormat ("yyyy-MMM-dd");

    public static String getBirthdayAsString (final Person p) {
        return BD_FORMAT.format (p.birthdayUTC);
    }

    public static Person get (final int id) {
        return map.get (id);
    }
}
