package com.map.mapmaxv1.activities;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.MarkDTO;
import com.map.mapmaxv1.dto.UserDTO;

import java.util.List;

/**
 * Created by User on 27.02.2017.
 */

public class RecyclerViewAdapterProfile extends RecyclerView.Adapter<RecyclerViewAdapterProfile.ViewHolder> {

    private List<UserDTO> mList;

    public RecyclerViewAdapterProfile(List<UserDTO> list) {
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
        switch (position)
        {
            case 0:
                holder.titleTextView.setText("Город: Санкт-Петербург");
                holder.imageView.setImageResource(R.drawable.ic_location_city_black_24dp);
                break;
            case 1:
                holder.titleTextView.setText("20 лет");
                holder.imageView.setImageResource(R.drawable.ic_tag_faces_black_24dp);
                break;
            case 2:
                holder.titleTextView.setText("Место работы: На дому");
                holder.imageView.setImageResource(R.drawable.ic_work_black_24dp);
                break;
            case 3:
                holder.titleTextView.setText("Стиилист, фотограф");
                holder.imageView.setImageResource(R.drawable.ic_list_black_24dp);
                break;
            case 4:
                holder.titleTextView.setText("Сертификаты: ");
                holder.imageView.setImageResource(R.drawable.ic_school_black_24dp);
                break;
            case 5:
                holder.titleTextView.setText("О себе: тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест");
                holder.imageView.setImageResource(R.drawable.ic_person_black_24dp);
                break;
            case 6:
                holder.titleTextView.setText("Все предложения");
                holder.imageView.setImageResource(R.drawable.ic_person_pin_circle_black_24dp);
                break;
            case 7:
                holder.titleTextView.setText("Отзывы");
                holder.imageView.setImageResource(R.drawable.ic_feedback_black_24dp);
                break;
        }

    }

    @Override
    public int getItemCount() {
        int t =8;
        return t;
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
