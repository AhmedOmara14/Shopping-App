package com.example.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {

    @BindView(R.id.username_Register)
    EditText usernameRegister;
    @BindView(R.id.password_Resgister)
    EditText passwordResgister;
    @BindView(R.id.btn_register)
    Button btnLogin;
    @BindView(R.id.gotologin)
    TextView gotologin;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private String email_register;
    private String email_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        intialization();
    }

    private void intialization() {
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
    }

    @OnClick({R.id.btn_register, R.id.gotologin})
    public void onViewClicked(View view) {
        email_register= usernameRegister.getText().toString();
        email_pass= passwordResgister.getText().toString();
        switch (view.getId()) {
            case R.id.btn_register:
                if (email_register.isEmpty()){
                    Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (email_pass.isEmpty()){
                    Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.createUserWithEmailAndPassword(email_register,email_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this, "Welcome", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.gotologin:
                Intent intent=new Intent(Register.this,login.class);
                startActivity(intent);
                break;
        }
    }
}
