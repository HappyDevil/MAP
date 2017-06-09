package com.map.mapmaxv1.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.map.mapmaxv1.CircularTransformation;
import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.MarkDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 10.03.2017.
 */

public class RecyclerViewAdapterList extends RecyclerView.Adapter<RecyclerViewAdapterList.ViewHolder>{

    private List<MarkDTO> mList;
    private Context context;

    public RecyclerViewAdapterList(List<MarkDTO> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_marker, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerViewAdapterList.ViewHolder holder, int position) {

        Picasso picasso = Picasso.with(context);

        picasso.load(R.drawable.voww)
                .transform(new CircularTransformation(0))
                .into(holder.profilePhoto);

        ImageView backfoto = new ImageView(context);



        picasso.load(R.drawable.voww)
                .transform(new CircularTransformation(0))
                .into(backfoto);

        holder.galleryMarker.setBackground(backfoto.getDrawable());


        holder.nameTextViewList.setText(mList.get(position).getFIO());
        holder.textViewList.setText("Описание: " + mList.get(position).getText());
        holder.priceTextViewList.setText(String.valueOf(mList.get(position).getPrice()) + " руб.");
        //holder.profilePhoto.setImageDrawable(drawable);
        holder.typeTextViewList.setText(mList.get(position).getType());
        holder.titleTextViewList.setText("Название: " + mList.get(position).getTitle());
        //holder.galleryMarker.setImageResource(R.drawable.z_9dc940eb);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextViewList;
        private TextView priceTextViewList;
        private TextView textViewList;
        private TextView addressViewList;
        private ImageView profilePhoto;
        private ImageView galleryMarker;
        private CardView cardView;
        private TextView typeTextViewList;
        private TextView titleTextViewList;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_m);
            nameTextViewList = (TextView)itemView.findViewById(R.id.textView16);
            titleTextViewList = (TextView)itemView.findViewById(R.id.textView18);
            priceTextViewList = (TextView)itemView.findViewById(R.id.textView19);
            textViewList = (TextView)itemView.findViewById(R.id.textView17);
            typeTextViewList = (TextView)itemView.findViewById(R.id.textView15);
            profilePhoto = (ImageView)itemView.findViewById(R.id.imageView4);
            galleryMarker = (ImageView)itemView.findViewById(R.id.imageView5);
            itemView.setTag(itemView);
        }
    }

}
