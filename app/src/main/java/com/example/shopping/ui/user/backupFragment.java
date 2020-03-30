package com.example.shopping.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.Back.adapterforbackbag;
import com.example.shopping.Back.backbag;
import com.example.shopping.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class backupFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    DatabaseReference reference;
    FirebaseAuth auth;
    ArrayList<backbag> list;
    adapterforbackbag  adapterforbackbag;
    public backupFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_backup, container, false);
        inialization();
        reference.child("items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    String type=dataSnapshot1.child("item_type").getValue().toString();
                    backbag i=dataSnapshot1.getValue(backbag.class);
                    if (type.equals("Back Bag")){
                        list.add(i);
                    }
                }
                adapterforbackbag=new adapterforbackbag(list,getContext());
                recyclerView.setAdapter(adapterforbackbag);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
    public void inialization(){
      recyclerView=view.findViewById(R.id.recyclerofbackup);
      recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
      reference= FirebaseDatabase.getInstance().getReference();
      auth=FirebaseAuth.getInstance();
      list=new ArrayList<backbag>();
    }

}
