package com.myapps.getcall_smsinfoanynumberthree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.myapps.getcall_smsinfoanynumberthree.R;
import com.myapps.getcall_smsinfoanynumberthree.model.SlideListItem;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private final Context mContext;
    List<SlideListItem> modelObjects;

    public CustomPagerAdapter(Context context, List<SlideListItem> modelObjects) {
        mContext = context;
        this.modelObjects = modelObjects;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_pager, collection, false);
        collection.addView(layout);
        ImageView iv_icon = layout.findViewById(R.id.iv_icon);
        TextView tv_tittle = layout.findViewById(R.id.tv_tittle);
        TextView tv_number = layout.findViewById(R.id.tv_number);
        TextView tv_time = layout.findViewById(R.id.tv_time);

        Glide.with(mContext).load(modelObjects.get(position).getIconUrl()).into(iv_icon);

        tv_tittle.setText(modelObjects.get(position).getTittle());
        tv_number.setText(modelObjects.get(position).getNumber());
        tv_time.setText(modelObjects.get(position).getTime());

        if (position == modelObjects.size() - 2) {
            runnable.run();
        }

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return modelObjects.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            modelObjects.addAll(modelObjects);
            notifyDataSetChanged();

        }
    };

}