package com.example.sanyam.myapp;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    //DatabaseReference dataCoo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
     //   sendData.setOnClickListener(new View.OnClickListener(){
       //     @Override
       //     public void onClick(View view){
      //          Update();
       //     }
       // });
        //dataCoo = FirebaseDatabase.getInstance().getReference("coordinates");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        LocationLat =(EditText)findViewById(R.id.etLocationLat);
        LocationLong =(EditText)findViewById(R.id.etLocationLong);
        final String z= LocationLat.getText().toString().trim();
        final String zz = LocationLong.getText().toString().trim();

        sendData=(Button) findViewById(R.id.sendData);
        url=new Firebase("https://myapp-f80a0.firebaseio.com/");
        //sendData.setOnClickListener(this);
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Firebase firebase = url.child("Name");
            firebase.setValue(z);
            firebase.setValue(z.concat(zz));
            //Firebase firebase2 = url.child("Value");  THE CONTENT IN COMMENTS ARE MY TRIALS
            //firebase2.setValue(LocationLat); THE CODE RUNS SUCCESSFULLY...
            }
        });


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
