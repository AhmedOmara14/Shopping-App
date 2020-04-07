package com.example.shopping.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    ArrayList<items> item;
    myadapter myadapter;
    ImageView imageView;
    EditText item_nam,type,price,des1;
    Button btn_update,btn_delete;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView=(RecyclerView)root.findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        imageView=root.findViewById(R.id.img_item);
        des1= root.findViewById(R.id.Des_r);
        item_nam=root.findViewById(R.id.name_item);
        price=root.findViewById(R.id.pri);
        type=root.findViewById(R.id.type_item);
        btn_update=root.findViewById(R.id.update);
        btn_delete=root.findViewById(R.id.btn_delete);

        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference().child("items");
        item=new ArrayList<items>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                     items i=dataSnapshot1.getValue(items.class);
                     item.add(i);

                 }
                 myadapter=new myadapter(getContext(),item);
                 recyclerView.setAdapter(myadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }


}
