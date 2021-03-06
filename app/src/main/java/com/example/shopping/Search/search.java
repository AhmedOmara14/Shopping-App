package com.example.shopping.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shopping.R;
import com.example.shopping.pojo.class_items;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    DatabaseReference reference;
    FirebaseAuth auth;
    adapterforsearch adapterforsearch;
    ArrayList<class_items> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView=findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(search.this));

        searchView=(SearchView) findViewById(R.id.search_view);
        reference= FirebaseDatabase.getInstance().getReference().child("items");
        auth=FirebaseAuth.getInstance();
        list=new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    class_items i=dataSnapshot1.getValue(class_items.class);
                    list.add(i);

                }
                 adapterforsearch=new adapterforsearch(list, search.this);
                recyclerView.setAdapter(adapterforsearch);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               search_it(newText);
               return true;
           }
       });
    }

    private void search_it(String v) {
        ArrayList<class_items> list1=new ArrayList<>();
        for (class_items class_items : list){
            if (class_items.getItem_name().toLowerCase().contains(v.toLowerCase())){
                list1.add(class_items);
            }
        }
        adapterforsearch=new adapterforsearch(list1,search.this);
        recyclerView.setAdapter(adapterforsearch);
    }
}

