package ca.mahram.android.androidjava8.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.mahram.android.androidjava8.R;

/**
 Created by mahramf on 2016-10-03.
 */

public final class People {
    // cast of Stranger things from IMDB som Birthdays are fake because they were missing
    private static final Person[] PEOPLE = new Person[] {
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

    @SuppressLint ("SimpleDateFormat")
    private static final SimpleDateFormat BD_FORMAT = new SimpleDateFormat ("YYYY-MMM-dd");

    static class PeopleCard extends RecyclerView.ViewHolder {

        @BindView (android.R.id.title) TextView name;
        @BindView (android.R.id.summary) TextView birthday;

        PeopleCard (final View itemView) {
            super (itemView);
            ButterKnife.bind (this, itemView);
            itemView.setTag (this);
        }
    }

    public static final class PeopleAdapter extends RecyclerView.Adapter<PeopleCard> {

        private final LayoutInflater inflater;
        private final List<Person> list;

        public PeopleAdapter (final Context context) {
            inflater = LayoutInflater.from (context);
            list = new ArrayList<> ();
            Collections.addAll (list, PEOPLE);
        }

        @Override public PeopleCard onCreateViewHolder (final ViewGroup parent, final int viewType) {
            return new PeopleCard (inflater.inflate (R.layout.card_person, parent, false));
        }

        @Override public void onBindViewHolder (final PeopleCard holder, final int position) {
            final Person person = list.get (position);
            holder.name.setText (person.fullName ());
            holder.birthday.setText (BD_FORMAT.format (person.birthdayUTC));
        }

        @Override public int getItemCount () {
            return list.size ();
        }

        public void defaultSort () {
            list.clear ();
            Collections.addAll (list, PEOPLE);
            notifyDataSetChanged ();
        }

        public void sortByAge () {
            Collections.sort (list, Person::compareByAge);
            notifyDataSetChanged ();
        }

        public void sortByFirstName () {
            Collections.sort (list, Person::compareByFirstName);
            notifyDataSetChanged ();
        }

        public void sortByLastName () {
            Collections.sort (list, Person::compareByLastName);
            notifyDataSetChanged ();
        }

        public void sortByFullName () {
            // intentionally left as lambda
            //noinspection Convert2MethodRef
            Collections.sort (list, (Person a, Person b) -> Person.compareByFullName (a, b));
            notifyDataSetChanged ();
        }
    }
}
