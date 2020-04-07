package com.example.shopping.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class login extends AppCompatActivity {

    @BindView(R.id.username_login)
    EditText usernameLogin;
    @BindView(R.id.password_login)
    EditText passwordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btntoregister)
    Button btntoregister;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private String email;
    private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        intialization();
        try {
            if (auth.getCurrentUser().getUid() != null){
                Intent intent=new Intent(login.this, Useractivity.class);
                startActivity(intent);

            }
        }
        catch (Exception e){

        }


    }

    private void intialization() {
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
    }

    @OnClick({R.id.btn_login, R.id.btntoregister})
    public void onViewClicked(View view) {
        email=usernameLogin.getText().toString();
        pass=passwordLogin.getText().toString();
        switch (view.getId()) {
            case R.id.btn_login:
                if (email.equals("ahmed@admin.com")&&pass.equals("123456789")){
                    Intent intent=new Intent(login.this, Adminpage.class);
                    startActivity(intent);
                }
                if (email.isEmpty()){
                    Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (pass.isEmpty()){
                    Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
                }

                else{
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(login.this, "Welcome", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(login.this, Useractivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                break;
            case R.id.btntoregister:
                Intent intent=new Intent(login.this, Register.class);
                startActivity(intent);
                break;
        }

    }
}
