package com.example.signupgooglefirebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    ProgressBar p;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        e1 = findViewById(R.id.editText1);
        e2 = findViewById(R.id.editText2);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD); //to type password in hidden type.
        p = findViewById(R.id.progressBar);
        p.setVisibility(View.INVISIBLE);
        firebaseAuth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString(), s2 = e2.getText().toString();
                if(s1.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill complete details", Toast.LENGTH_SHORT).show();
                } else{
                    p.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.INVISIBLE);
                    e2.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                p.setVisibility(View.INVISIBLE);
                                e1.setVisibility(View.VISIBLE);
                                e2.setVisibility(View.VISIBLE);
                                b1.setVisibility(View.VISIBLE);
                                Toast.makeText(MainActivity.this, "Database updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                p.setVisibility(View.INVISIBLE);
                                e1.setVisibility(View.VISIBLE);
                                e2.setVisibility(View.VISIBLE);
                                b1.setVisibility(View.VISIBLE);
                                Toast.makeText(MainActivity.this, "Database updation unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}