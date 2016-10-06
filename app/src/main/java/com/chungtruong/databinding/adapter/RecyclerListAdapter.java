package com.chungtruong.databinding.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.databinding.ListItemBinding;
import com.chungtruong.databinding.model.MoreApp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chung.truong on 10/4/2016.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.MainListHolder> {

    private List<MoreApp> list;

    public RecyclerListAdapter(List<MoreApp> list) {
        this.list = list;
    }

    @Override
    public MainListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MainListHolder(view);
    }

    @Override
    public void onBindViewHolder(MainListHolder holder, int position) {
        final MoreApp moreApp = list.get(position);
        holder.binding.setVariable(com.chungtruong.databinding.BR.moreapp, moreApp);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @BindingAdapter("app:imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .into(imageView);
    }

    public static class MainListHolder extends RecyclerView.ViewHolder {

        ListItemBinding binding;

        public MainListHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
