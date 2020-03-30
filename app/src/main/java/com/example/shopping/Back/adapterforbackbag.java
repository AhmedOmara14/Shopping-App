package com.example.shopping.Back;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.showitems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterforbackbag extends RecyclerView.Adapter<adapterforbackbag.myviewholder>{
    ArrayList<backbag> list ;
    Context context;

    public adapterforbackbag(ArrayList<backbag> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(context).inflate(R.layout.recommended_rec,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Picasso.get().load(list.get(position).getImages()).into(holder.imageView_rec);
        holder.itemnameforrec.setText(list.get(position).getItem_name());
        holder.priceforrec.setText(list.get(position).getItem_price());
        String item_name=list.get(position).getItem_name();
        String item_price=list.get(position).getItem_price();
        String item_image=list.get(position).getImages();
        String item_des= list.get(position).getItem_Des();
        Intent intent=new Intent(context, showitems.class);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("item_name",item_name);
                intent.putExtra("item_price",item_price);
                intent.putExtra("item_image",item_image);
                intent.putExtra("item_des",item_des);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView_rec;
        TextView priceforrec,itemnameforrec;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView_rec=itemView.findViewById(R.id.images_rec);
            priceforrec=itemView.findViewById(R.id.price_rec);
            itemnameforrec=itemView.findViewById(R.id.item_rec);
        }
    }
}
