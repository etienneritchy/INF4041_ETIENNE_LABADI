package org.esiea.labadi.etienne.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class GetBiersServices extends IntentService {


    public GetBiersServices() {
        super("GetBiersServices");
    }


    public static void startActionBier(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        context.startService(intent);


    }


    @Override
    protected void onHandleIntent(Intent intent) {
        handleActionBiers();
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(country_activity.BIERS_UPDATE));
    }




    private void handleActionBiers() {
        Log.d(TAG, "thread service name:" + Thread.currentThread().getName());
       // Toast.makeText(getApplicationContext(),"bieres json download", Toast.LENGTH_LONG).show();
        URL url;

        try {
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if(HttpURLConnection.HTTP_OK != connection.getResponseCode()){
                copyInputStreamToFile(connection.getInputStream(),
                        new File(getCacheDir(),"bieres.json"));
                Toast.makeText(getApplicationContext(),"bieres json download", Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    private void copyInputStreamToFile(InputStream in, File file){
        try{
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }


}
