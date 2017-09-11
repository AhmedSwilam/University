package com.example.android.university;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by Ahmed on 8/14/2017.
 */

public class Adapter extends ArrayAdapter {
    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }
}
