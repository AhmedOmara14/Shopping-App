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
import com.example.shopping.R;
import com.example.shopping.Recommended.adapterforrecommended;
import com.example.shopping.pojo.class_items;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import com.example.shopping.Popular19.adapterforpopular;


public class HomeUserFragment extends Fragment {

    RecyclerView recPopular19;
    RecyclerView recRecommended;
    ArrayList<class_items> list=new ArrayList<>();
    ArrayList<class_items> list_rec=new ArrayList<>();
    private com.example.shopping.Popular19.adapterforpopular adapterforpopular;
    com.example.shopping.Recommended.adapterforrecommended adapterforrecommended;
    Repositriy repositriy;
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
        adapterforpopular=new adapterforpopular(list,getContext());
        recPopular19.setAdapter(adapterforpopular);

         adapterforrecommended=new adapterforrecommended(list_rec,getContext());
         recRecommended.setAdapter(adapterforrecommended);


         repositriy= ViewModelProviders.of(this).get(Repositriy.class);

        repositriy.getitem().observe((LifecycleOwner) getContext(), new Observer<List<class_items>>() {
            @Override
            public void onChanged(List<class_items> class_items) {
                adapterforpopular.setList((ArrayList<com.example.shopping.pojo.class_items>) class_items);
            }
        });

        repositriy.getitem_recom().observe((LifecycleOwner) getContext(), new Observer<List<class_items>>() {
            @Override
            public void onChanged(List<class_items> class_items) {
                adapterforrecommended.setList((ArrayList<com.example.shopping.pojo.class_items>) class_items);

            }
        });

        return view;

    }



}
