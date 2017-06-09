package com.map.mapmaxv1.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.map.mapmaxv1.R;

import java.util.List;

/**
 * Created by User on 10.05.2017.
 */

public class RecyclerViewAdapterComment extends RecyclerView.Adapter<RecyclerViewAdapterComment.ViewHolder> {

    private List clist;

    public RecyclerViewAdapterComment(List list) {
        this.clist = list;
    }

    @Override
    public RecyclerViewAdapterComment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_comment, parent, false);
        return new RecyclerViewAdapterComment.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterComment.ViewHolder holder, int position) {
        holder.titleTextView.setText("Максим Попков");
        holder.titleTextView1.setText("Рейтинг: ");
        holder.titleTextView2.setText("Подписчики");
        holder.titleTextView3.setText("Оценка");
        holder.titleTextView4.setText("Дата");
        holder.titleTextView5.setText("Текст отзыва");
        holder.imageView.setImageResource(R.drawable.voww);
    }

    @Override
    public int getItemCount() {
        return clist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView titleTextView1;
        private TextView titleTextView2;
        private TextView titleTextView3;
        private TextView titleTextView4;
        private TextView titleTextView5;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.textView13);
            titleTextView1 = (TextView)itemView.findViewById(R.id.textView20);
            titleTextView2 = (TextView)itemView.findViewById(R.id.textView8);
            titleTextView3 = (TextView)itemView.findViewById(R.id.textView21);
            titleTextView4 = (TextView)itemView.findViewById(R.id.textView11);
            titleTextView5 = (TextView)itemView.findViewById(R.id.textView12);
            imageView = (ImageView)itemView.findViewById(R.id.imageView9);
            itemView.setTag(itemView);
        }
    }
}