package org.esiea.labadi.etienne.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;


public class SecondActivity extends AppCompatActivity {

    ListView vue;
    String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Khaled", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Ritchy", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy","Wid" , "Xavier",
            "Yann", "Zoé"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        vue = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, prenoms);
        vue.setAdapter(adapter);
    }

    private void handleActionBiers() {
        final String USER_AGENT = "Mozilla/5.0";
        // Toast.makeText(getApplicationContext(),"bieres json download", Toast.LENGTH_LONG).show();
        try {
            URL obj = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            Log.d(TAG, "\nSending 'GET' request to URL : http://binouze.fabrigli.fr/bieres.json");
            Log.d(TAG, "Code : " +responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                Toast.makeText(getApplicationContext(),"bieres json download", Toast.LENGTH_LONG).show();

            }
            in.close();


        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
