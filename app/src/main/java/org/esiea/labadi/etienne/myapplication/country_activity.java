package org.esiea.labadi.etienne.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static org.esiea.labadi.etienne.myapplication.country_activity.BIERS_UPDATE;


public class country_activity extends AppCompatActivity {

    public static final String BIERS_UPDATE ="com.octip.cours.inf4042_11.BIERS_UPDATE";

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

        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new country_activity.BierUpdate(),intentFilter);

    }

    public class BierUpdate extends BroadcastReceiver {
        private Context context;



        @Override
        public void onReceive(Context context, Intent intent) {
        showNotification();
        }

        public void showNotification() {

            NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(context)
                            .setContentTitle("My notification")
                            .setContentText("Ca Marche!");

            mbuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
            mbuilder.setAutoCancel(true);

            Intent notificationIntent = new Intent(BIERS_UPDATE);
            PendingIntent contentIntent = PendingIntent.getActivity(country_activity.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mbuilder.setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, mbuilder.build());

        }


    }

    //https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json
    //json
}
