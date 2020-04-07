package com.example.shopping.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Useractivity extends AppCompatActivity {
    @BindView(R.id.tool)
    ImageButton tool;
    private ViewPager myviewpager;
    private TabLayout mytabLayout;
    private com.example.shopping.ui.main.tabaccess tabaccess;

  //  String firebaseUser;
    FirebaseAuth auth;

    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kk);
        ButterKnife.bind(this);
        intialization();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Useractivity.this, com.example.shopping.Search.search.class);
                startActivity(intent);
            }
        });
    }

    public void intialization() {
        myviewpager = (ViewPager) findViewById(R.id.view_pager);
        mytabLayout = (TabLayout) findViewById(R.id.tabs);
        tabaccess = new tabaccess(getSupportFragmentManager());
        myviewpager.setAdapter(tabaccess);
        mytabLayout.setupWithViewPager(myviewpager);
        auth = FirebaseAuth.getInstance();
        search=findViewById(R.id.search);
       //     Intent intent=new Intent(Useractivity.this,login.class);
          //  startActivity(intent);
       // }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.useractivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_Logout) {
            Intent intent = new Intent(Useractivity.this, login.class);
            startActivity(intent);
        }
        return true;

    }


    public void showmenu(View view){
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.adminpage, popup.getMenu());
        popup.show();
    }


}
