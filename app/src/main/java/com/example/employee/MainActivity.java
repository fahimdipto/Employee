package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button click;
    public static TextView dd;
    ListView nameListView;
    ArrayList<String>  allLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click=(Button) findViewById(R.id.bt1);
        nameListView = findViewById(R.id.nameListView);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data process= new data();
                process.execute();
                Log.d("myTag", "This is my message");

                ArrayList<String> allNames  = data.nameData;
                allLocation  = data.locationData;
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, allNames.toArray());
                nameListView.setAdapter(adapter);
            }
        });
        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(MainActivity.this,MapsActivity.class);
                i.putExtra("data",allLocation.get(position));
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(),"Loading Map",Toast.LENGTH_SHORT).show();
            }
        });


    }
}