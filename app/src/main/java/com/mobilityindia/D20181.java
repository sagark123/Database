package com.mobilityindia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class D20181 extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private TextView mNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d20181);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mNameView=(TextView)findViewById(R.id.cn1);

    }
}
