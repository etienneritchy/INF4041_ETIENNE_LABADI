package org.esiea.labadi.etienne.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private static final IntentFilter BIERS_UPDATE = ;
    private static Button button_sbm;
    private static Button button_s;
    private static Button button_s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startService(new Intent(getBaseContext(), GetBiersServices.class));
        OnClickButtonListener();
        OnClickButtonListener2();
        OnClickButtonListener3();
        // onOptionsItemSelected();
    }



    public void OnClickButtonListener(){

/*        Button button = (Button) findViewById(R.id.buttonToast);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(),
                        "Button is clicked", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(" org.esiea.labadi.etienne.myapplication.country_activity");
                        startActivity(intent);
                    }
                }
        );*/
        button_s = (Button) findViewById(R.id.buttonToast);
        button_s.setOnClickListener(
                 new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Toast.makeText(getApplicationContext(),"Button is clicked", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent("org.esiea.labadi.etienne.myapplication.country_activity");
                        startActivity(intent);
                 }
                }
        );
    }

    public void OnClickButtonListener2(){
        button_sbm = (Button) findViewById(R.id.button);
        button_sbm.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Toast.makeText(getApplicationContext(),"Button is clicked", Toast.LENGTH_LONG).show();
                       // stopService(new Intent(getBaseContext(), GetBiersServices.class));
                        Intent intent = new Intent("org.esiea.labadi.etienne.myapplication.SecondActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickButtonListener3(){
       // startService(new Intent(MainActivity.this, GetBiersServices.class));
        button_s2 = (Button) findViewById(R.id.button2);
        button_s2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Toast.makeText(getApplicationContext(),"Button is clicked", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent("org.esiea.labadi.etienne.myapplication.inscription");
                        startActivity(intent);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: Implement this method
       MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.testmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

            switch (item.getItemId()) {
                case R.id.about:
                    Intent intent = new Intent(this, about.class);
                    startActivity(intent);
                    return true;
                case R.id.settings:
                    Intent intentS = new Intent(this, setting.class);
                    startActivity(intentS);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

    }




}