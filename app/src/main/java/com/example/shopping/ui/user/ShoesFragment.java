package com.example.shopping.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.shoes.adapterforshoeses;
import com.example.shopping.shoes.shoes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShoesFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference reference;
    View view;
    FirebaseAuth auth;
    ArrayList<shoes> list;
    adapterforshoeses adapterforshoeses;
    public ShoesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_shoes, container, false);
        intialization();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                   String type=dataSnapshot1.child("item_type").getValue().toString();
                   shoes i=dataSnapshot1.getValue(shoes.class);
                   if (type.equals("Shoes")){
                       list.add(i);
                   }
               }
               adapterforshoeses=new adapterforshoeses(list,getContext());
               recyclerView.setAdapter(adapterforshoeses);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void intialization() {
        recyclerView=view.findViewById(R.id.rec_shoes);
        reference= FirebaseDatabase.getInstance().getReference().child("items");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        auth=FirebaseAuth.getInstance();
        list=new ArrayList<shoes>();
    }

}
