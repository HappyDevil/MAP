package com.map.mapmaxv1.activities;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.MarkDTO;

import java.util.List;

/**
 * Created by User on 10.03.2017.
 */

public class RecyclerViewAdapterList extends RecyclerView.Adapter<RecyclerViewAdapterList.ViewHolder>{

    private List<MarkDTO> mList;

    public RecyclerViewAdapterList(List<MarkDTO> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterList.ViewHolder holder, int position) {
        holder.nameTextViewList.setText(position+1 + ". " + mList.get(position).getUser());
        holder.textViewList.setText(mList.get(position).getText());
        holder.priceTextViewList.setText(String.valueOf(mList.get(position).getPrice()));
        holder.profilePhoto.setImageResource(R.drawable.z_9dc940eb);
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
        private TextView jobViewList;
        private ImageView profilePhoto;
        private ImageButton galleryMarker;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cv);
            nameTextViewList = (TextView)itemView.findViewById(R.id.textView3);
            priceTextViewList = (TextView)itemView.findViewById(R.id.textView5);
            textViewList = (TextView)itemView.findViewById(R.id.textView4);
            profilePhoto = (ImageView)itemView.findViewById(R.id.imageView2);
            galleryMarker = (ImageButton)itemView.findViewById(R.id.imageButton3);
            itemView.setTag(itemView);
        }

        public ViewHolder(View itemView, boolean b) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.infowindow);
            addressViewList = (TextView)itemView.findViewById(R.id.textView10);
            nameTextViewList = (TextView)itemView.findViewById(R.id.textView9);
            priceTextViewList = (TextView)itemView.findViewById(R.id.textView13);
            textViewList = (TextView)itemView.findViewById(R.id.textView11);
            jobViewList = (TextView)itemView.findViewById(R.id.textView12);
            profilePhoto = (ImageView)itemView.findViewById(R.id.imageView3);
            itemView.setTag(itemView);
        }

        public TextView getAddressViewList() {
            return addressViewList;
        }

        public void setAddressViewList(TextView addressViewList) {
            this.addressViewList = addressViewList;
        }

        public TextView getJobViewList() {
            return jobViewList;
        }

        public void setJobViewList(TextView jobViewList) {
            this.jobViewList = jobViewList;
        }

        public TextView getNameTextViewList() {
            return nameTextViewList;
        }

        public void setNameTextViewList(TextView nameTextViewList) {
            this.nameTextViewList = nameTextViewList;
        }

        public TextView getPriceTextViewList() {
            return priceTextViewList;
        }

        public void setPriceTextViewList(TextView priceTextViewList) {
            this.priceTextViewList = priceTextViewList;
        }

        public TextView getTextViewList() {
            return textViewList;
        }

        public void setTextViewList(TextView textViewList) {
            this.textViewList = textViewList;
        }

        public ImageView getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(ImageView profilePhoto) {
            this.profilePhoto = profilePhoto;
        }

        public ImageButton getGalleryMarker() {
            return galleryMarker;
        }

        public void setGalleryMarker(ImageButton galleryMarker) {
            this.galleryMarker = galleryMarker;
        }

        public CardView getCardView() {
            return cardView;
        }

        public void setCardView(CardView cardView) {
            this.cardView = cardView;
        }
    }



}
