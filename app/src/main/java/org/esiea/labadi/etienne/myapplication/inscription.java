package org.esiea.labadi.etienne.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

import static android.content.ContentValues.TAG;


public class inscription extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
    }

    private void handleActionBiers() {
        Log.d(TAG, "thread service name:" + Thread.currentThread().getName());
        URL url = null;

        try {
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
         /*   if(HttpURLConnection.HTTP_OK != connection.getResponseCode){
                copyInputStreamToFile(connection.getInputStream(),
                new File(getCacheDir(),"bieres.json"));
                Log.d(TAG,"bieres json download");
            }*/
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    catch(Exception e) {
            e.printStackTrace();
        }

    }
    private void copyInputStreamToFile(InputStream in,File file){
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
    //public JSONArray getBiersFromFile()


}