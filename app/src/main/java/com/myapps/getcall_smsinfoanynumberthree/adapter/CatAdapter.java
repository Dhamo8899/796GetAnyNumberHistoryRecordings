package com.myapps.getcall_smsinfoanynumberthree.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.activity.MobileActivity;
import com.myapps.getcall_smsinfoanynumberthree.activity.SelectHistoryActivity;
import com.myapps.getcall_smsinfoanynumberthree.model.CatListItem;
import com.myapps.getcall_smsinfoanynumberthree.utils.Const;
import com.pesonal.adsdk.AppManage;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CatListItem> imagesList;
    private SelectHistoryActivity context;

    public CatAdapter(List<CatListItem> imagesList, SelectHistoryActivity context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivIcon;
        public TextView tvTittle;
        public TextView tvSubtittle;

        public MyViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.ivIcon);
            tvTittle = view.findViewById(R.id.tvTittle);
            tvSubtittle = view.findViewById(R.id.tvSubtittle);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        Glide.with(context).load(imagesList.get(position).getIconUrl()).into(myViewHolder.ivIcon);
        myViewHolder.tvTittle.setText(imagesList.get(position).getTittle());
        myViewHolder.tvSubtittle.setText(imagesList.get(position).getSubTittle());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.isOnline(context)){
                    AppManage.getInstance(context).showInterstitialAd(context, new AppManage.MyInterStitialCallback() {
                        @Override
                        public void callbackCall() {
                            Intent intent = new Intent(context, MobileActivity.class);
                            context.startActivity(intent);
                            context.overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                    }, AppManage.app_mainClickCntSwAd);
                }else {
                    Toast.makeText(context,"Please check your internet connection!!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesList != null ? imagesList.size() : 0;
    }
}
