package org.esiea.labadi.etienne.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class country_activity extends AppCompatActivity {


    ListView simpleList;
    String [] countryList = {"haiti", "china", "sweden", "portugal", "usa", "france"};
    int [] flags = {R.drawable.haiti, R.drawable.china, R.drawable.sweden, R.drawable.portugal, R.drawable.usa, R.drawable.france};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"page showed", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_country_activity);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        Myadapter Myadapter = new Myadapter(country_activity.this, countryList, flags);
        simpleList.setAdapter(Myadapter);
    }
}
