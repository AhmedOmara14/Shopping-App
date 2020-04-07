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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class backupFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    ArrayList<class_items> list;
    adapterforbackbag  adapterforbackbag;
    public backupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_backup, container, false);
        inialization();
        Repositriy repositriy= ViewModelProviders.of(this).get(Repositriy.class);
        adapterforbackbag=new adapterforbackbag(list, getContext());
        recyclerView.setAdapter(adapterforbackbag);
        repositriy.getData().observe((LifecycleOwner) getContext(), new Observer<List<class_items>>() {
            @Override
            public void onChanged(List<class_items> class_items) {
                adapterforbackbag.setList((ArrayList<com.example.shopping.pojo.class_items>) class_items);
            }
        });
        return view;
    }
    public void inialization(){
      recyclerView=view.findViewById(R.id.recyclerofbackup);
      recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
      list=new ArrayList<class_items>();
    }

}
