package ca.mahram.android.androidjava8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
  extends AppCompatActivity {

    @BindView (R.id.textView) TextView textView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ButterKnife.bind (this);

        textView.post (()-> Toast.makeText (MainActivity.this, R.string.lambda, Toast.LENGTH_SHORT).show ());
    }
}
