package ca.mahram.android.androidjava8.model;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import ca.mahram.android.androidjava8.R;

/**
 Created by mahramf on 2016-10-03.
 */

public enum SortMode {
    Age (R.string.age),
    FirstName (R.string.first_name),
    LastName (R.string.last_name),
    FullName (R.string.full_name);

    @StringRes public final int displayName;

    SortMode (final int displayName) {
        this.displayName = displayName;
    }
}
