package com.mobilityindia;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class P2017 extends AppCompatActivity {

    Button button7,button8;
    //Put 20171 child name here
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("");
    // Put 20172 child name here
    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("");
    String pdf_20171;
    String pdf_20172;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2017);
        button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                try {
                    downloadManager.enqueue(new DownloadManager.Request(Uri.parse(pdf_20171))
                            .setTitle("P20171")
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            );

                } catch (Exception e) {
                    Log.e("TAG", "link wrong");
                    Toast.makeText(P2017.this, "Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                try {
                    downloadManager.enqueue(new DownloadManager.Request(Uri.parse(pdf_20172))
                            .setTitle("P20172")
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    );

                } catch (Exception e) {
                    Log.e("TAG", "link wrong");
                    Toast.makeText(P2017.this, "Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    pdf_20171 = dataSnapshot.getValue(String.class);
                } catch (Exception e) {
                    Log.e("TAG", "pdf rulebook " + dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    pdf_20172 = dataSnapshot.getValue(String.class);
                } catch (Exception e) {
                    Log.e("TAG", "pdf rulebook " + dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
