package ca.mahram.android.androidjava8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import ca.mahram.android.androidjava8.adapter.SortModeSpinnerAdapter;
import ca.mahram.android.androidjava8.model.People;
import ca.mahram.android.androidjava8.model.SortMode;

public class MainActivity
  extends AppCompatActivity {

    @BindView (R.id.sortMode)     Spinner      sortMode;
    @BindView (android.R.id.list) RecyclerView list;

    private People.PeopleAdapter peopleAdapter;

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
        peopleAdapter = new People.PeopleAdapter (this);
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
}
