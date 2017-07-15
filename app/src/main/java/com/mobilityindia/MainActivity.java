package com.mobilityindia;

import android.app.Activity;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText input_email,input_password;
    TextView btnSignup,btnForgotPass;

    RelativeLayout activity_main;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view
        btnLogin=(Button)findViewById(R.id.login_button);
        input_email=(EditText)findViewById(R.id.login_email);
        input_password=(EditText)findViewById(R.id.login_password);
        btnSignup=(TextView)findViewById(R.id.sign_up);
        btnForgotPass=(TextView)findViewById(R.id.forgot_password);
        activity_main=(RelativeLayout)findViewById(R.id.activity_main);

        btnSignup.setOnClickListener(this);
        btnForgotPass.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //Init Firebase Auth
        auth=FirebaseAuth.getInstance();

        //Check already session, if ok--->dashboard

        if(auth.getCurrentUser() !=null)
            startActivity(new Intent(MainActivity.this,dashboard.class));
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.forgot_password)
        {
            startActivity(new Intent (MainActivity.this,Forgotpassword.class));
            finish();
        }
        else  if(view.getId()==R.id.sign_up)
        {
            startActivity(new Intent (MainActivity.this,Signup.class));
            finish();
        }
        else  if(view.getId()==R.id.login_button)
        {
            loginUser(input_email.getText().toString(),input_password.getText().toString());
        }
    }

    private void loginUser(String email,final String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            if (password.length() < 6)
                            {
                                Snackbar snackbar = Snackbar.make(activity_main, "Password should be more than 6 characters", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        }
                        else
                        {
                            startActivity(new Intent(MainActivity.this, dashboard.class));
                        }
                    }

                });

    }
}
