package ca.mahram.android.androidjava8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ca.mahram.android.androidjava8.R;
import ca.mahram.android.androidjava8.model.SortMode;

/**
 Created by mahramf on 2016-10-03.
 */

public final class SortModeSpinnerAdapter
  extends BaseAdapter {
    private final LayoutInflater inflater;
    private final SortMode [] values = SortMode.values ();

    public SortModeSpinnerAdapter (final Context context) {
        inflater = LayoutInflater.from (context);
    }

    @Override public int getCount () {
        return values.length;
    }

    @Override public SortMode getItem (final int position) {
        return values[position];
    }

    @Override public long getItemId (final int position) {
        return position;
    }

    private TextView newView (final ViewGroup parent) {
        return (TextView) inflater.inflate (R.layout.item_spinner, parent, false);
    }

    @Override public View getView (final int position, final View convertView, final ViewGroup parent) {
        final TextView tv = null == convertView ? newView (parent) : (TextView) convertView;
        tv.setText (getItem (position).displayName);
        return tv;
    }
}
