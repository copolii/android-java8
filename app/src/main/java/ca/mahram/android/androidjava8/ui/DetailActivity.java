package ca.mahram.android.androidjava8.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.mahram.android.androidjava8.R;
import ca.mahram.android.androidjava8.annotations.Photo;
import ca.mahram.android.androidjava8.model.People;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.N;

/**
 Created by mahramf on 2016-10-04.
 */

public final class DetailActivity extends AppCompatActivity {
    private static final String LOGTAG  = "Detail";

    @BindView (android.R.id.list) RecyclerView grid;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail);
        ButterKnife.bind (this);

        final ActionBar ab = getSupportActionBar ();
        if (null != ab)
            ab.setDisplayHomeAsUpEnabled (true);

        grid.setLayoutManager (new GridLayoutManager (this, 2, LinearLayoutManager.VERTICAL, false));
        grid.setAdapter (new PeoplePhotosAdapter (this));
    }

    @Override public boolean onOptionsItemSelected (final MenuItem item) {
        if (item.getItemId () == android.R.id.home){
            finish ();
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    static class PersonImageHolder extends RecyclerView.ViewHolder {

        @BindView (android.R.id.title) TextView title;
        @BindView (android.R.id.icon) ImageView image;

        PersonImageHolder (final View itemView) {
            super (itemView);
            ButterKnife.bind (this, itemView);
            itemView.setTag (this);
        }
    }

    private static class PhotoItem {
        final Uri uri;
        final String title;

        PhotoItem (final Uri u, final String t) {
            uri = u;
            title = t;
        }
    }

    @TargetApi (N)
    public static PhotoItem[] extractPhotos () {
        if (SDK_INT < N)
            return new PhotoItem[0];

        final List<PhotoItem> photoUris = new ArrayList<> ();

        for (final Photo photo : People.class.getAnnotationsByType (Photo.class)) {
            final String uriStr = photo.value ();
            Log.d (LOGTAG, "Photo: " + uriStr);
            final Uri uri = Uri.parse (uriStr);

            if (null == uri) continue;

            photoUris.add (new PhotoItem (uri, photo.title ()));
        }

        Log.d (LOGTAG, photoUris.size () + " photo uris");

        return photoUris.toArray (new PhotoItem[photoUris.size ()]);
    }

    class PeoplePhotosAdapter
      extends RecyclerView.Adapter <PersonImageHolder> {

        private final PhotoItem[] photos;
        private final LayoutInflater inflater;

        PeoplePhotosAdapter (final Context context) {
            inflater = LayoutInflater.from (context);
            photos = extractPhotos ();
        }

        @Override public PersonImageHolder onCreateViewHolder (final ViewGroup parent, final int viewType) {
            return new PersonImageHolder (inflater.inflate (R.layout.item_person_image, parent, false));
        }

        @Override public void onBindViewHolder (final PersonImageHolder holder, final int position) {
            final PhotoItem photo = photos[position];
            Picasso.with (DetailActivity.this)
                   .load (photo.uri)
                   .resizeDimen (R.dimen.image_dimen, R.dimen.image_dimen)
                   .centerCrop ()
                   .into (holder.image);
            holder.title.setText (photo.title);
        }

        @Override public int getItemCount () {
            return photos.length;
        }
    }
}
