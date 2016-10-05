package ca.mahram.android.androidjava8.annotations;

import android.annotation.TargetApi;
import android.os.Build;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 Created by mahramf on 2016-10-04.
 */
@TargetApi (Build.VERSION_CODES.N)
@Retention (RetentionPolicy.RUNTIME)
@Repeatable (Photos.class)
public @interface Photo {
    String value();
    String title () default "";
}
