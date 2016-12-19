package org.esiea.labadi.etienne.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class SecondActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    ListView vue;


    public static ArrayList<String> arrayList;
    public static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        myDb = new DataBaseHelper(this);

        vue = (ListView) findViewById(R.id.listView);
        String[] prenoms = new String[]{
                "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
                "Gerard", "Hugo", "Ingrid", "Jonathan", "Khaled", "Kevin", "Logan",
                "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Ritchy", "Romain",
                "Sophie", "Tristan", "Ulric", "Vincent", "Willy","Wid" , "Xavier",
                "Yann", "Zoé"
        };
        arrayList=new ArrayList<>(Arrays.asList(prenoms));
        adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, prenoms);
        vue.setAdapter(adapter);
       viewAll();

    }

 //   public void maj(){

   //     SecondActivity.arrayList.add(newitemglobal);
   //     SecondActivity.adapter.notifyDataSetChanged();
   // }
   public void viewAll() {


                       Cursor res = myDb.getAllData();
                       if(res.getCount() == 0) {
                           // show message
                           Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_LONG).show();
                           return;
                       }

                       StringBuffer buffer = new StringBuffer();
                       while (res.moveToNext()) {
                           //buffer.append("Id :"+ res.getString(0)+"\n");
                           buffer.append("Name :"+ res.getString(1)+"\n");
                       }

                       // Show all data
                      // showMessage("Data",buffer.toString());
      // SecondActivity.arrayList.add(buffer.name);
      //     SecondActivity.adapter.notifyDataSetChanged();


   }




}
