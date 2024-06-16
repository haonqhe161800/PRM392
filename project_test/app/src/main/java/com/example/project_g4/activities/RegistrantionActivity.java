package com.example.project_g4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_g4.R;
import com.example.project_g4.models.Usermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrantionActivity extends AppCompatActivity {
    Button signUp;
    EditText name, email, password;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrantion);


        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();
        signUp = findViewById(R.id.register_btn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.password_reg);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrantionActivity.this, LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                progressBar.setVisibility(v.VISIBLE);
            }
        });
    }

    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(RegistrantionActivity.this, "Name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(RegistrantionActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPass.length() < 6) {
            Toast.makeText(RegistrantionActivity.this, "Password length must be greater than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // create user
        auth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Usermodel usermodel = new Usermodel(userName,userEmail,userPass);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(usermodel);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrantionActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(RegistrantionActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
