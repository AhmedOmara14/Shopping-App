package com.example.shopping.ui.main.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.Back.Repositriy;
import com.example.shopping.Back.adapterforbackbag;
import com.example.shopping.pojo.class_items;
import com.example.shopping.R;
import com.example.shopping.shoes.adapterforshoeses;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ShoesFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    ArrayList<class_items> list;
    adapterforshoeses adapterforshoeses;
    Repositriy repositriy;
    public ShoesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_shoes, container, false);
        intialization();
        Repositriy repositriy= ViewModelProviders.of(this).get(Repositriy.class);
        adapterforshoeses=new adapterforshoeses(list, getContext());
        recyclerView.setAdapter(adapterforshoeses);
         repositriy.getitem_shoes().observe((LifecycleOwner) getContext(), new Observer<List<class_items>>() {
            @Override
            public void onChanged(List<class_items> class_items) {
                adapterforshoeses.setList((ArrayList<com.example.shopping.pojo.class_items>) class_items);
            }
        });
        return view;
    }

    private void intialization() {
        recyclerView=view.findViewById(R.id.rec_shoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<class_items>();
    }

}
