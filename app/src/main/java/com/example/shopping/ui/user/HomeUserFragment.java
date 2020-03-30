package com.example.shopping.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.Popular19.items_popular_Recomended;
import com.example.shopping.R;
import com.example.shopping.Recommended.adapterforrecommended;
import com.example.shopping.Recommended.item_recommended;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.example.shopping.Popular19.adapterforpopular;


public class HomeUserFragment extends Fragment {

    RecyclerView recPopular19;
    RecyclerView recRecommended;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    ArrayList<items_popular_Recomended> list;
    ArrayList<item_recommended> list_rec;
    private com.example.shopping.Popular19.adapterforpopular adapterforpopular;
    com.example.shopping.Recommended.adapterforrecommended adapterforrecommended;
    public HomeUserFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home_user, container, false);

        recPopular19=view.findViewById(R.id.rec_popular19);
        recPopular19.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));



        recRecommended=view.findViewById(R.id.rec_recommended);
        recRecommended.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference().child("items");
        list=new ArrayList<items_popular_Recomended>();
        list_rec=new ArrayList<item_recommended>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                     String type=dataSnapshot1.child("item_type").getValue().toString();

                     items_popular_Recomended i=dataSnapshot1.getValue(items_popular_Recomended.class);
                     item_recommended a=dataSnapshot1.getValue(item_recommended.class);

                     if (type.equals("Home")) {
                         list.add(i);
                     }
                     if (type.equals("Recommended")) {
                         list_rec.add(a);
                     }
                 }


                adapterforrecommended=new adapterforrecommended(list_rec, getContext());
                recRecommended.setAdapter(adapterforrecommended);

                adapterforpopular=new adapterforpopular(list, getContext());
                recPopular19.setAdapter(adapterforpopular);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;

    }



}
