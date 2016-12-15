package com.chungtruong.databinding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.databinding.SongItemBinding;
import com.chungtruong.databinding.model.Song;

import java.util.List;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        final Song song = songList.get(position);
        holder.binding.setSong(song);
    }

    @Override
    public int getItemCount() {
        return songList != null ? songList.size() : 0;
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {

        private SongItemBinding binding;

        public SongViewHolder(View itemView) {
            super(itemView);
            binding = SongItemBinding.bind(itemView);
        }
    }
}
