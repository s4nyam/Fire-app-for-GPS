package com.fireapp.sanyam.myapp;

import android.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    private Button sendData;
    Firebase url;
    // private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference rootRef=firebaseDatabase.getReference();
    private DatabaseReference databaseReference;
    EditText LocationLat;
    EditText LocationLong;
    EditText editTextName;
    DatabaseReference dataName;
    //DatabaseReference dataCoo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataName = FirebaseDatabase.getInstance().getReference("name");
        Firebase.setAndroidContext(this);
        //   sendData.setOnClickListener(new View.OnClickListener(){
        //     @Override
        //     public void onClick(View view){
        //          Update();
        //     }
        // });
        //dataCoo = FirebaseDatabase.getInstance().getReference("coordinates");
        editTextName = (EditText) findViewById(R.id.editTextName);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        LocationLat =(EditText)findViewById(R.id.etLocationLat);
        LocationLong =(EditText)findViewById(R.id.etLocationLong);
        final String z= LocationLat.getText().toString().trim();
        final String zz = LocationLong.getText().toString().trim();
        final Date currentTime = Calendar.getInstance().getTime();
        sendData=(Button) findViewById(R.id.sendData);
        url=new Firebase("https://myapp-f80a0.firebaseio.com/");
        //sendData.setOnClickListener(this);
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase firebase = url.child("Location").push();
                String name = editTextName.getText().toString();
                Name nameuser = new Name(name);

                firebase.setValue(nameuser);
                firebase.setValue(z);
                firebase.setValue( name.toUpperCase() +" "+ z.concat(zz).concat(name)+currentTime.toString().trim() + " "+ name);
                Toast.makeText(MainActivity.this, "Updated with sanyam firebase database. Go to website dashboard to check the location", Toast.LENGTH_SHORT).show();
                //Firebase firebase2 = url.child("Value");  THE CONTENT IN COMMENTS ARE MY TRIALS
                //firebase2.setValue(LocationLat); THE CODE RUNS SUCCESSFULLY...
            }
        });

    }
    private void addName(){
        String name = editTextName.getText().toString();
        if(!TextUtils.isEmpty(name)){

        Name username = new Name(name);
        dataName.setValue(username);
        }else{
            Toast.makeText(this,"You should enter your name",Toast.LENGTH_LONG).show();
        }
    }



    void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();

                ((EditText) findViewById(R.id.etLocationLat)).setText("Latitude: " + latti);
                ((EditText) findViewById(R.id.etLocationLong)).setText("Longitude: " + longi);
            } else {
                ((EditText) findViewById(R.id.etLocationLat)).setText("Unable to find correct location.");
                ((EditText) findViewById(R.id.etLocationLong)).setText("Unable to find correct location. ");
            }
        }

    }


    //private void Update(){
    //String latti = etLocationLat.getText().toString();
    //String longi = etLocationLong.getText().toString();

    //dataCoo.push().getKey();
    //coordinates coo = new coordinates(latti,longi);
    // dataCoo.setValue(coo);

    // }


}