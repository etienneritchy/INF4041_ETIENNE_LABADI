package org.esiea.labadi.etienne.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ritchy on 08/11/2016.
 */

public class Myadapter extends BaseAdapter {
    Context context;
    String countryList[];
    int flags[];
    LayoutInflater inflater;


    public Myadapter(Context applicationContext, String[] countryList, int[] flags) {
        //this.context = context;
        this.countryList = countryList;
        this.flags = flags;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

     static class ViewHolder {
         public ImageView image;
         public TextView text;
     }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
        view = inflater.inflate(R.layout.activity_country_activity, null);
        viewHolder = new ViewHolder();
        viewHolder.text = (TextView) view.findViewById(R.id.textView);
        viewHolder.image = (ImageView) view.findViewById(R.id.icon);
            viewHolder.text.setText(countryList[i]);
            viewHolder.image.setImageResource(flags[i]);
        //country.setText(countryList[i]);
        //icon.setImageResource(flags[i]);
            view.setTag(viewHolder);
    }
    else {
        viewHolder = (ViewHolder) view.getTag();
    }
    return view;
}
}
