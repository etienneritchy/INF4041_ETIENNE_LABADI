package org.esiea.labadi.etienne.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;


public class country_activity extends AppCompatActivity {



    ListView simpleList;
    String[] countryList = {"haiti", "china", "sweden", "portugal", "usa", "france"};
    int[] flags = {R.drawable.haiti, R.drawable.china, R.drawable.sweden, R.drawable.portugal, R.drawable.usa, R.drawable.france};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "page showed", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_country_activity);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        Myadapter Myadapter = new Myadapter(country_activity.this, countryList, flags);
        simpleList.setAdapter(Myadapter);
       // GetBiersServices.startActionBiers(getApplicationContext());
        //rv = (RecyclerView) findViewById(R.id.rv_bier);
       // rv.setLayoutManager(new RelativeLayout(this, RelativeLayoutManager.VERTICAL, false));
      //  rv.setAdapter(new BiersAdapter(new JSONArray()));
       // IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
     //   LocalBroadcastManager.getInstance(this).registerReceiver(new country_activity.BierUpdate(),intentFilter);

    }








    //https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json
    //json
}
