package com.example.android.university;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.university.R.layout.list_row;

/**
 * Created by Ahmed on 8/13/2017.
 */

public class NewsArrayAdapter extends ArrayAdapter<String> {
    TextView TITLE;
    TextView Desc;
    Context adaptercontext;
    int adapterlayout;
    String[] adapternews;
    private ArrayList<String> arrayList;


    public NewsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] newslist) {
        super(context,R.layout.list_row,newslist);

        adaptercontext = context;
        adapterlayout = list_row;
         adapternews = newslist;

    }




    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        LayoutInflater inflater = LayoutInflater.from(adaptercontext);
        row = inflater.inflate(list_row, parent, false);
        TextView Desc = (TextView) row.findViewById(R.id.desc);
        TextView TITLE = (TextView) row.findViewById(R.id.title);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageview);
//        News newq = adapternews[position];
//        TITLE.setText(newq.title);
//        Desc.setText(newq.desc);
//        int image = adaptercontext.getResources().getIdentifier(newq.image, "drawable", adaptercontext.getPackageName());
//        imageView.setImageResource(image);
        return row;



    }


    }












