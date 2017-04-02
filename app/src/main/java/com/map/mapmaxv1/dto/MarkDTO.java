package com.map.mapmaxv1.dto;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Date;

public class MarkDTO implements Parcelable {
    private long id; // ID
    private String text; // Описание
    private String user; // ниик нейм пользователя
    private Date date;  // Дата создания марки
    private int price;  // Цена
    private String type; // Тип работы
    private double lat; // Широта
    private double lng; // Долгота

    private String FIO; //  ФИО пользователя
    private boolean visible; // Видимость


    public MarkDTO() {
    }


    protected MarkDTO(Parcel in) {
        id = in.readLong();
        text = in.readString();
        user = in.readString();
        price = in.readInt();
        type = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        FIO = in.readString();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeString(user);
        dest.writeInt(price);
        dest.writeString(type);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(FIO);
        dest.writeByte((byte)(visible?1:0));
    }
}
