package com.example.shopping.Popular19;

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
import com.example.shopping.pojo.class_items;
import com.example.shopping.ui.main.showitems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapterforpopular extends RecyclerView.Adapter<adapterforpopular.myviewholder> {
    List<class_items> list=new ArrayList<>();
    Context context;


    public adapterforpopular(List<class_items> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adapterforpopular.myviewholder(LayoutInflater.from(context).inflate(R.layout.popular,parent,false));
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
    public void setList(ArrayList<class_items> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView_rec;
        TextView priceforrec,itemnameforrec;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView_rec=itemView.findViewById(R.id.imgforpopular);
            priceforrec=itemView.findViewById(R.id.priceforpopular);
            itemnameforrec=itemView.findViewById(R.id.item_nameforpopular);
        }
    }
}
