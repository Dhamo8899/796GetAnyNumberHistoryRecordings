package com.myapps.getcall_smsinfoanynumberthree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.model.CatListItem;

import java.util.List;

public class PurchaseCatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CatListItem> imagesList;
    private Context context;

    public PurchaseCatAdapter(List<CatListItem> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProTittle;

        public MyViewHolder(View view) {
            super(view);
            tvProTittle = view.findViewById(R.id.tvProTittle);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_line, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvProTittle.setText(imagesList.get(position).getTittle());
    }

    @Override
    public int getItemCount() {
        return imagesList != null ? imagesList.size() : 0;
    }
}
