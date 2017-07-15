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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    Button btnSignup;
    TextView btnLogin,btnforgotpass;
    EditText input_email,input_pass;
    RelativeLayout activity_signup;

    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //view
        btnSignup=(Button)findViewById(R.id.register_button);
        btnLogin=(TextView)findViewById(R.id.signup_login);
        btnforgotpass=(TextView)findViewById(R.id.signup_forgot_password);
        input_email=(EditText)findViewById(R.id.signup_email);
        input_pass=(EditText)findViewById(R.id.signup_password);
        activity_signup=(RelativeLayout)findViewById(R.id.Signup);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnforgotpass.setOnClickListener(this);

        //init Firebase
        auth= FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View view){
        if(view.getId()==R.id.signup_login)
        {
            startActivity(new Intent(Signup.this,MainActivity.class));
        }
        else if(view.getId()==R.id.signup_forgot_password){
            startActivity(new Intent(Signup.this,Forgotpassword.class));
            finish();
        }
        else if(view.getId()==R.id.register_button) {
            SignUpUser(input_email.getText().toString(),input_pass.getText().toString());
        }
    }
    private void SignUpUser(String email,String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            snackbar= Snackbar.make(activity_signup,"Error: "+task.getException(),snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else
                        {
                            snackbar= Snackbar.make(activity_signup,"Register success :",snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }
                });
    }


}