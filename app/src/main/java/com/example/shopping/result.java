package com.example.shopping;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class result extends AppCompatActivity {

    @BindView(R.id.item_name_result)
    TextView itemNameResult;
    @BindView(R.id.item_quantity_result)
    TextView itemQuantityResult;
    @BindView(R.id.item_price)
    TextView itemPrice;
    @BindView(R.id.total_order)
    TextView totalOrder;
    String item_name_resule1,item_price_result1,quantity1;
    int total_pric,total_quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        item_name_resule1=getIntent().getExtras().get("item_name").toString();
        item_price_result1=getIntent().getExtras().get("item_price").toString();
        quantity1=getIntent().getExtras().get("item_quantity").toString();

        itemNameResult.setText(item_name_resule1);
        itemPrice.setText(item_price_result1);
        itemQuantityResult.setText(quantity1);
        totalOrder.setText(Integer.parseInt(quantity1)*Integer.parseInt(item_price_result1)+"");
    }
}
