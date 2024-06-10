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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button sigIn;
    EditText email, password;
    TextView signUp;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        sigIn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUp = findViewById(R.id.sign_up);

        // Chuyển sang màn hình đăng ký
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrantionActivity.class));
            }
        });

        // Đăng nhập người dùng
        sigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);

                loginUser();
            }
        });
    }

    private void loginUser() {
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            progressBar.setVisibility(View.GONE);

            Toast.makeText(LoginActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPass.length() < 6) {
            progressBar.setVisibility(View.GONE);

            Toast.makeText(LoginActivity.this, "Password length must be greater than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Đăng nhập người dùng
        auth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            // Chuyển hướng sang màn hình khác nếu đăng nhập thành công
                            // startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
