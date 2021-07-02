package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    ImageView appLogo;
    EditText emailForgotPassword;
    Button sendResetPasswordLinkButton;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        appLogo = findViewById(R.id.app_logo_forget_password);
        emailForgotPassword = findViewById(R.id.email_forget_password);
        sendResetPasswordLinkButton = findViewById(R.id.send_password_reset_link_button);
        progressBar = findViewById(R.id.reset_password_progressbar);
        mAuth = FirebaseAuth.getInstance();


        sendResetPasswordLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailForgotPassword.getText().toString().trim();

                if(email.isEmpty()) {
                    Toast.makeText(ForgetPasswordActivity.this, "Enter your E-mail first...", Toast.LENGTH_SHORT).show();
                }
                else {

                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {

                                Toast.makeText(ForgetPasswordActivity.this, "Password Reset Link has been sent to your E-mail", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(ForgetPasswordActivity.this, "Account doesn't exit. Please Enter correct E-mail", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });

    }
}