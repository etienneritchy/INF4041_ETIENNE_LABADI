package org.esiea.labadi.etienne.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class about extends AppCompatActivity {


    private static Button button_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Onclickmail();
    }
    public void Onclickmail(){
        button_mail = (Button) findViewById(R.id.button3);
        button_mail.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Toast.makeText(getApplicationContext(),"Button is clicked", Toast.LENGTH_LONG).show();
                        Intent intent;
                        try {
                            intent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.email");
                            if (intent == null)
                                throw new PackageManager.NameNotFoundException();
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(intent);
                        } catch (PackageManager.NameNotFoundException e) {
                            // affiche que l'appli n'es pas présente sur le tel
                        }
                    }
                }
        );
    }

}
