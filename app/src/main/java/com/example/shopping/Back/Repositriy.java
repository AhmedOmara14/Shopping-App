package com.example.shopping.Back;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopping.pojo.class_items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Repositriy extends ViewModel {
    public static Repositriy instance;
     ArrayList<class_items> list=new ArrayList<>();
     DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("items");
    public MutableLiveData<List<class_items>> listMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<List<class_items>> listMutableLiveData_shoes=new MutableLiveData<>();
    public MutableLiveData<List<class_items>> listMutableLiveData_popular=new MutableLiveData<>();
    public MutableLiveData<List<class_items>> listMutableLiveData_recommended=new MutableLiveData<>();

    ArrayList<class_items> list2=new ArrayList<>();
    ArrayList<class_items> list3=new ArrayList<>();

    ArrayList<class_items> list_rec=new ArrayList<>();
    public static Repositriy getinstance(){
        if (instance==null){
             instance=new Repositriy();
        }
        return instance;
    }
    public MutableLiveData<List<class_items>> getData() {
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String type = dataSnapshot1.child("item_type").getValue().toString();
                    class_items i = dataSnapshot1.getValue(class_items.class);
                    if (type.equals("Back Bag")) {
                        list.add(i);
                        listMutableLiveData.setValue(list);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

          return listMutableLiveData;
    }
    public MutableLiveData<List<class_items>> getitem(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    String type=dataSnapshot1.child("item_type").getValue().toString();

                    class_items i=dataSnapshot1.getValue(class_items.class);
                    if (type.equals("Home")) {
                        list2.add(i);
                        listMutableLiveData_popular.setValue(list2);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return listMutableLiveData_popular;
    }

    public MutableLiveData<List<class_items>> getitem_recom(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    String type=dataSnapshot1.child("item_type").getValue().toString();

                    class_items i=dataSnapshot1.getValue(class_items.class);

                    if (type.equals("Recommended")) {
                        list_rec.add(i);
                        listMutableLiveData_recommended.setValue(list_rec);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return listMutableLiveData_recommended;
    }
    public MutableLiveData<List<class_items>> getitem_shoes(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    String type=dataSnapshot1.child("item_type").getValue().toString();

                    class_items i=dataSnapshot1.getValue(class_items.class);

                    if (type.equals("Shoes")) {
                        list3.add(i);
                        listMutableLiveData_shoes.setValue(list3);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return listMutableLiveData_shoes;
    }

}