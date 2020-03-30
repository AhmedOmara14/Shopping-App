package com.example.shopping.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.Recommended.adapterforrecommended;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterforsearch extends RecyclerView.Adapter<adapterforsearch.myviewholder>{
    ArrayList<search_item> list;
    Context context;

    public adapterforsearch(ArrayList<search_item> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adapterforsearch.myviewholder(LayoutInflater.from(context).inflate(R.layout.recommended_rec,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Picasso.get().load(list.get(position).getImages()).into(holder.imageView_rec);
        holder.itemnameforrec.setText(list.get(position).getItem_name());
        holder.priceforrec.setText(list.get(position).getItem_price());
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
