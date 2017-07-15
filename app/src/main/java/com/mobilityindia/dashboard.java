package com.mobilityindia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    private TextView textwelcome;
    private EditText input_new_password;
    private Button btnChangepass,btnProceed,btnLogout;
    private RelativeLayout activity_dashboard;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //view
        textwelcome=(TextView)findViewById(R.id.dashboard_welcome);
        input_new_password=(EditText)findViewById(R.id.dashboard_newpassword);
        btnChangepass=(Button)findViewById(R.id.dashboard_btn_changepassword);
        btnProceed=(Button)findViewById(R.id.Proceed);
        btnLogout=(Button)findViewById(R.id.Logout);
        activity_dashboard=(RelativeLayout)findViewById(R.id.dashboard);

        btnChangepass.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        //Init Firebase

        auth=FirebaseAuth.getInstance();

        //session check

        if(auth.getCurrentUser() !=null)
            textwelcome.setText("Welcome ,"+auth.getCurrentUser().getEmail());
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.dashboard_btn_changepassword)
            changePassword(input_new_password.getText().toString());
        else if(view.getId() == R.id.Logout)
            logoutUser();
        else if(view.getId() ==R.id.Proceed)
            proceedNext();
    }

    private void proceedNext(){
        if(auth.getCurrentUser() !=null)
        {
            startActivity(new Intent(dashboard.this,Menu.class));
            finish();
        }

    }

    private void logoutUser() {
        auth.signOut();
        if(auth.getCurrentUser() ==null)
        {
            startActivity(new Intent(dashboard.this,MainActivity.class));
            finish();
        }
    }

    private void changePassword(String newPassword)
    {
        FirebaseUser user= auth.getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Snackbar snackbar = Snackbar.make(activity_dashboard, "Password Changed", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        } );
    }
}
