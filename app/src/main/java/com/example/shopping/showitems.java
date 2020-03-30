package com.example.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping.ui.user.HomeUserFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class showitems extends AppCompatActivity {

    ImageView back;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.moins)
    TextView moins;
    TextView str;
    @BindView(R.id.plus)
    TextView plus;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.item_des)
    TextView itemDes;
    Button btnAddToCart;
    String item_name,it_image,item_price,item_des;

    int count ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showitems);
        ButterKnife.bind(this);
        str=findViewById(R.id.str);
        back=findViewById(R.id.back);
        btnAddToCart=findViewById(R.id.btn_add_to_cart);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(showitems.this, Useractivity.class);
                startActivity(intent);
            }
        });
        item_name=getIntent().getExtras().get("item_name").toString();
        it_image=getIntent().getExtras().get("item_image").toString();
        item_price=getIntent().getExtras().get("item_price").toString();
        item_des=getIntent().getExtras().get("item_des").toString();
        Picasso.get().load(it_image).into(img4);
        itemName.setText(item_name);
        itemDes.setText(item_des);
        price.setText(item_price+"$");
        count=Integer.parseInt(str.getText().toString());
    }

    @OnClick({R.id.moins, R.id.plus, R.id.btn_add_to_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.moins:
                if (count > 1 ){
                    count--;
                    str.setText(count+"");
                    Toast.makeText(this, count+"", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.plus:
               count++;
               str.setText(count+"");
               Toast.makeText(this, count+"", Toast.LENGTH_SHORT).show();
               break;
            case R.id.btn_add_to_cart:
                Intent intent=new Intent(showitems.this, result.class);
                intent.putExtra("item_name",item_name);
                intent.putExtra("item_price",item_price);
                intent.putExtra("item_quantity",count+"");
                startActivity(intent);
                break;
        }
    }
}
