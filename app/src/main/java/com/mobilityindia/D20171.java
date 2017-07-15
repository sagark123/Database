package com.mobilityindia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class D20171 extends AppCompatActivity {
    private TextView mCustomer01;
    private TextView mEPC01;
    private TextView mSystem01;
    private TextView mRPS01;
    private TextView mPartnerint01;
    private TextView mPartnerext01;

    private Firebase mRootref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d20171);

        mRootref= new Firebase("https://moblity-india.firebaseio.com/");
        mCustomer01=(TextView)findViewById(R.id.Customer01);
        mEPC01=(TextView)findViewById(R.id.EPC01);
        mSystem01=(TextView)findViewById(R.id.System01);
        mRPS01=(TextView)findViewById(R.id.RPS01);
        mPartnerext01=(TextView)findViewById(R.id.Partnersint01);
        mPartnerext01=(TextView)findViewById(R.id.Partnersext01);

    }
}

