package com.map.mapmaxv1.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.UserDTO;

import java.util.List;

/**
 * Created by User on 07.05.2017.
 */

public class RecyclerViewAdapterFollow extends RecyclerView.Adapter<RecyclerViewAdapterFollow.ViewHolder> {

    private List flist;

    public RecyclerViewAdapterFollow(List list) {
        this.flist = list;
    }

    @Override
    public RecyclerViewAdapterFollow.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.test_item, parent, false);
        return new RecyclerViewAdapterFollow.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterFollow.ViewHolder holder, int position) {
        holder.titleTextView.setText("Максим Попков");
        holder.imageView.setImageResource(R.drawable.voww);
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.textView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView6);
            itemView.setTag(itemView);
        }
    }
}
