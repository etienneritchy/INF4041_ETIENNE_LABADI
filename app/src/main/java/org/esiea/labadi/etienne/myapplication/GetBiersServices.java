package org.esiea.labadi.etienne.myapplication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetBiersServices extends IntentService {

    public static final String GET_BIERS ="org.esiea.labadi.etienne.myapplication.GET_BIERS";
    final String TAG = "SERVICE";

    public GetBiersServices() {
        super("GetBiersServices");

    }
    public static void startActionbiers(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        intent.setAction(GET_BIERS);
        context.startService(intent);


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (GET_BIERS.equals(action)) {
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();

                handleActionBiers();

            }
        }

    }
    private void handleActionBiers() {
        Toast.makeText(getApplicationContext(), "test2", Toast.LENGTH_LONG).show();

            Log.d(TAG, "Thread service name: " + Thread.currentThread().getName());
            URL url = null;
            try {
                url = new URL("https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json"
                );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                    copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "bieres.json"));
                    Log.d(TAG, "bieres.json downloaded!");
                    Toast.makeText(getApplicationContext(),"biers json dowload", Toast.LENGTH_LONG).show();
                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(inscription.BIERS_UPDATE));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    private void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







    /*private static final String TAG = "GetBiersServices";

    public GetBiersServices() {
        super("GetBiersServices");
        Toast.makeText(getApplicationContext(),"NIGGA WE MADE IT", Toast.LENGTH_LONG).show();
    }

    public static void startActionBiers(Context context) {
             Intent intent = new Intent(context, GetBiersServices.class);
            context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
      //  handleActionBiers();
       // LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(country_activity.BIERS_UPDATE));
   // }

  //  private void handleActionBiers() {
      *//*   final String USER_AGENT = "Mozilla/5.0";
       // Toast.makeText(getApplicationContext(),"bieres json download", Toast.LENGTH_LONG).show();
        try {
            Toast.makeText(getApplicationContext(),"le try", Toast.LENGTH_LONG).show();

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
        }*//*
        if (intent != null) {
            Log.d(TAG, "Thread service name:" + Thread.currentThread().getName());
            URL url = null;
            try {
                url = new URL("http://binouze.fabrigli.fr/bieres.json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                    copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "bieres.json"));
                    Log.d(TAG, "Bieres json downloaded !");

                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(country_activity.BierUpdate));


                } else {
                    Log.d(TAG, "p");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void copyInputStreamToFile(InputStream inputStream, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
