package com.map.mapmaxv1.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.map.mapmaxv1.R;

/**
 * Created by User on 27.02.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private String[] mList;
    public RecyclerViewAdapter(String[] list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.test_item, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(mList[position]);
    }
    @Override
    public int getItemCount() {
        return mList.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.textView);
            itemView.setTag(itemView);
        }
    }
}
