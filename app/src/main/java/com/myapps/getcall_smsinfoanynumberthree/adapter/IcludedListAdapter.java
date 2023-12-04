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
import com.myapps.getcall_smsinfoanynumberthree.model.IncludedListItem;

import java.util.List;

public class IcludedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IncludedListItem> imagesList;
    private Context context;

    public IcludedListAdapter(List<IncludedListItem> imagesList, Context context) {
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_include_line, parent, false);
        return new IcludedListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IcludedListAdapter.MyViewHolder myViewHolder = (IcludedListAdapter.MyViewHolder) holder;
        myViewHolder.tvProTittle.setText(imagesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return imagesList != null ? imagesList.size() : 0;
    }
}
