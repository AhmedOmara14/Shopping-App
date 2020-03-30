package com.example.shopping.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
    Context context;
    ArrayList<items> items;
    DatabaseReference reference;
    FirebaseAuth auth;
    String messageReceiverID ;
String currentuserid;

    public myadapter(Context context, ArrayList<com.example.shopping.ui.gallery.items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(context).inflate(R.layout.layot_rec_listitem,parent,false));
    }

    @Override
    public  void  onBindViewHolder(@NonNull myviewholder myviewholder, int position) {
        reference= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();
        currentuserid=auth.getCurrentUser().getUid();
        Picasso.get().load(items.get(position).getImages()).into(myviewholder.imageView);
        myviewholder.des1.setText(items.get(position).getItem_Des());
        myviewholder.item.setText(items.get(position).getItem_name());
        myviewholder.price.setText(items.get(position).getItem_price());
        myviewholder.type.setText(items.get(position).getItem_type());

                myviewholder.btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HashMap<String,Object> map=new HashMap<>();
                        map.put("item_name",myviewholder.item.getText().toString());
                        map.put("item_price",myviewholder.price.getText().toString());
                        map.put("item_Des",myviewholder.des1.getText().toString());
                        map.put("item_type",myviewholder.type.getText().toString());
                        reference.child("items").child(items.get(position).getId()).updateChildren(map);
                    }
                });
               myviewholder.btn_delete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       reference.child("items").child(items.get(position).getId()).removeValue();
                   }
               });






    }




    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        EditText item,type,price,des1;
        Button btn_update,btn_delete;


        public  myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item);
            des1= itemView.findViewById(R.id.Des_r);
            item=itemView.findViewById(R.id.name_item);
            price=itemView.findViewById(R.id.pri);
            type=itemView.findViewById(R.id.type_item);
            btn_update=itemView.findViewById(R.id.update);
            btn_delete=itemView.findViewById(R.id.btn_delete);
        }


    }
        }


