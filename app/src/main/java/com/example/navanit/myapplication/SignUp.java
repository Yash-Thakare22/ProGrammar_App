package com.example.navanit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//import com.example.navanit.myapplication.R;
//import com.example.smartlibapp.StudentActivities.Nav_Home_Stud;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth1;
    private EditText emailedt1;
    private EditText passedt1;
    private EditText passedtagain;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth1 = FirebaseAuth.getInstance();
        emailedt1 = findViewById(R.id.emailsignup);
        passedt1 = findViewById(R.id.passsignup);
        passedtagain = findViewById(R.id.passsignupagain);
        signUpBtn = findViewById(R.id.signupbtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailedt1.getText().toString().isEmpty() || !passedt1.getText().toString().isEmpty() || !passedtagain.getText().toString().isEmpty()){
                    signUp();
                }else{
                    Toast.makeText(SignUp.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void signUp(){
        String email = emailedt1.getText().toString();
        String password = passedt1.getText().toString();
        String passagain = passedtagain.getText().toString();
        if (passagain.equals(password)){
            mAuth1.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(new Intent(SignUp.this, Camera.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }

    }
}
