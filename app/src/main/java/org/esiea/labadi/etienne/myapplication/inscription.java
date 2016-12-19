package org.esiea.labadi.etienne.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class inscription extends AppCompatActivity {
    DataBaseHelper myDb;

    public static final String BIERS_UPDATE ="com.octip.cours.inf4042_11.BIERS_UPDATE";
    private RecyclerView rv;
    private Intent intent;
    private static Button button_V;

    //final EditText editname = (EditText) findViewById(R.id.editText);
    //private RecyclerView.Adapter BiersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        myDb = new DataBaseHelper(this);

        rv = (RecyclerView) findViewById(R.id.rv_bier);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv.setAdapter(new BiersAdapter(getBiersFromFile()));

        GetBiersServices.startActionbiers(this);
        IntentFilter intentFilter = new IntentFilter(inscription.BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),intentFilter);

        OnClickButtonListener();
    }

    private void OnClickButtonListener() {


        //EditText contentET = (EditText) findViewById(R.id.editText);
        //String string = contentET.getText().toString();
        final EditText editname = (EditText) findViewById(R.id.editText);
        button_V = (Button) findViewById(R.id.button4);
        button_V.setOnClickListener(
                 new View.OnClickListener(){
                    @Override
                    public void onClick (View v) {
                        String newitem = editname.getText().toString();
                        boolean isInserted = myDb.insertData(newitem);
                        if (isInserted == true)
                            Toast.makeText(inscription.this, "Data Inserted in database", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(inscription.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                       // Intent intent = new Intent("org.esiea.labadi.etienne.myapplication.MainActivity");
                     //  startActivity(intent);

                 }

                       // SecondActivity.arrayList.add(newitem);
                       // SecondActivity.adapter.notifyDataSetChanged();


                 }

        );
    }


    public JSONArray getBiersFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public class BierUpdate extends BroadcastReceiver {
        private Context context;


        @Override
        public void onReceive(Context context, Intent intent) {
            if (rv != null) {
                rv.setAdapter(new BiersAdapter(getBiersFromFile()));
                ((BiersAdapter) rv.getAdapter()).setBiers(getBiersFromFile());
               // Toast.makeText(getApplicationContext(), "if on recever", Toast.LENGTH_LONG).show();
            }
           // Toast.makeText(getApplicationContext(), "onreceiver", Toast.LENGTH_LONG).show();
            //showNotification();
            }

    }

        public void showNotification() {


            Context context =null;
            NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(context)
                    .setContentTitle("My notification")
                    .setContentText("Ca Marche!");

            mbuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
            mbuilder.setAutoCancel(true);

            Intent notificationIntent = new Intent(BIERS_UPDATE);
            PendingIntent contentIntent = PendingIntent.getActivity(inscription.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mbuilder.setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, mbuilder.build());

        }


   


    private class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {
        private JSONArray biers;

        BiersAdapter(JSONArray biers) {
            this.biers = biers;
        }


        @Override
        public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element, parent, false);
            return new BierHolder(v);
        }

        @Override
        public void onBindViewHolder(BierHolder holder, int position) {
            try {
                holder.name.setText(biers.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return biers.length();
        }

        public void setBiers(JSONArray array) {
            this.biers = array;
            notifyDataSetChanged();

        }

        class BierHolder extends RecyclerView.ViewHolder {
            private TextView name;
            BierHolder(View itemView) {
                super(itemView);
                this.name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);
            }
        }
    }
}

/*    private class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

        private JSONArray biers;

        BiersAdapter(JSONArray biers) {
            this.biers = biers;
        }

        void setBiers(JSONArray biers) {
            this.biers = biers;
           // showNotification();
        }

        @Override
        public BiersAdapter.BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element, parent, false);
            return new BierHolder(v);
        }

        @Override
        public void onBindViewHolder(BiersAdapter.BierHolder holder, int position) {
            try {
                holder.name.setText(biers.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return biers.length();
        }

        class BierHolder extends RecyclerView.ViewHolder {
            private TextView name;
            BierHolder(View itemView) {
                super(itemView);
                this.name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);
            }
        }
    }*/