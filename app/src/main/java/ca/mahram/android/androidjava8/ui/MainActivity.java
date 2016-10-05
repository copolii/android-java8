package ca.mahram.android.androidjava8.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import ca.mahram.android.androidjava8.R;
import ca.mahram.android.androidjava8.adapter.SortModeSpinnerAdapter;
import ca.mahram.android.androidjava8.model.Person;
import ca.mahram.android.androidjava8.model.SortMode;

import static ca.mahram.android.androidjava8.model.People.PEOPLE;
import static ca.mahram.android.androidjava8.model.People.getBirthdayAsString;

public class MainActivity
  extends AppCompatActivity {

    @BindView (R.id.sortMode)     Spinner      sortMode;
    @BindView (android.R.id.list) RecyclerView list;

    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ButterKnife.bind (this);

        initializeSpinner ();
        initializePeople ();

        /* The old, boring, Java 7 way
        list.post (new Runnable () {
            @Override public void run () {
                Toast.makeText (MainActivity.this, R.string.lambda, Toast.LENGTH_SHORT).show ();
            }
        });
        */

        // The new, exciting, Java 8 Lambda way
        list.post (() -> Toast.makeText (MainActivity.this, R.string.lambda, Toast.LENGTH_SHORT).show ());
    }

    private void initializePeople () {
        peopleAdapter = new PeopleAdapter (this);
        list.setLayoutManager (new LinearLayoutManager (this));
        list.setAdapter (peopleAdapter);
    }

    private void initializeSpinner () {
        sortMode.setAdapter (new SortModeSpinnerAdapter (this));
    }

    @OnItemSelected (R.id.sortMode) public void onSpinnerItemSelected (final int position) {
        final SortMode selected = (SortMode) sortMode.getItemAtPosition (position);

        switch (selected) {
            case Age:
                peopleAdapter.sortByAge ();
                break;
            case FirstName:
                peopleAdapter.sortByFirstName ();
                break;
            case LastName:
                peopleAdapter.sortByLastName ();
                break;
            case FullName:
                peopleAdapter.sortByFullName ();
                break;
            default:
                peopleAdapter.defaultSort ();
                break;
        }
    }

    @OnClick(android.R.id.button1) public void showPhotos () {
        startActivity (new Intent (this, DetailActivity.class));
    }

    static class PeopleCard extends RecyclerView.ViewHolder {

        @BindView (android.R.id.title)   TextView name;
        @BindView (android.R.id.summary) TextView birthday;

        PeopleCard (final View itemView) {
            super (itemView);
            ButterKnife.bind (this, itemView);
            itemView.setTag (this);
        }
    }

    public static final class PeopleAdapter extends RecyclerView.Adapter<PeopleCard> {

        private final LayoutInflater inflater;
        private final List<Person>   list;

        PeopleAdapter (final Context context) {
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
            holder.birthday.setText (getBirthdayAsString (person));
        }

        @Override public int getItemCount () {
            return list.size ();
        }

        void defaultSort () {
            list.clear ();
            Collections.addAll (list, PEOPLE);
            notifyDataSetChanged ();
        }

        void sortByAge () {
            Collections.sort (list, Person::compareByAge);
            notifyDataSetChanged ();
        }

        void sortByFirstName () {
            Collections.sort (list, Person::compareByFirstName);
            notifyDataSetChanged ();
        }

        void sortByLastName () {
            Collections.sort (list, Person::compareByLastName);
            notifyDataSetChanged ();
        }

        void sortByFullName () {
            // intentionally left as lambda
            //noinspection Convert2MethodRef
            Collections.sort (list, (Person a, Person b) -> Person.compareByFullName (a, b));
            notifyDataSetChanged ();
        }
    }
}
