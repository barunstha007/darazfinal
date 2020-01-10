package com.example.darazfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.darazfinal.R;
import com.example.darazfinal.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

        Context context;

        List<Item> itemList;
public ItemAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
        }

@NonNull
@Override
public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display,parent,false);
        return new ItemViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
final Item item=itemList.get(position);
        holder.tvDescription.setText(item.getProductName());
        holder.tvRate.setText(item.getPrice());
//        holder.imgImage.setImageResource(item.getProductImage());
        }

@Override
public int getItemCount() {
        return itemList.size();
        }

public class ItemViewHolder extends RecyclerView.ViewHolder{
    ImageView imgImage;
    TextView tvDescription,tvRate;
    public ItemViewHolder(@NonNull View itemView){
        super(itemView);
        imgImage=itemView.findViewById(R.id.imgImage);
        tvDescription=itemView.findViewById(R.id.tvDescription);
        tvRate=itemView.findViewById(R.id.tvRate);
    }
}
}
