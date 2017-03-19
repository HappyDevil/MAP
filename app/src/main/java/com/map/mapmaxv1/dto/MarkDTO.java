package com.map.mapmaxv1.dto;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Date;

public class MarkDTO implements Parcelable {
    private String title; //тип работы
    private String text;
    private String user;
    private String FIO;
    private Date date;
    private int price;
    private String type;
    private double lat;
    private double lng;
    private boolean visible;


    public MarkDTO() {
    }


    protected MarkDTO(Parcel in) {
        title = in.readString();
        text = in.readString();
        user = in.readString();
        FIO = in.readString();
        price = in.readInt();
        type = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        visible = in.readByte() != 0;
    }

    public static final Creator<MarkDTO> CREATOR = new Creator<MarkDTO>() {
        @Override
        public MarkDTO createFromParcel(Parcel in) {
            return new MarkDTO(in);
        }

        @Override
        public MarkDTO[] newArray(int size) {
            return new MarkDTO[size];
        }
    };

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(user);
        dest.writeString(FIO);
        dest.writeInt(price);
        dest.writeString(type);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeByte((byte)(visible?1:0));
    }
}
