package com.map.mapmaxv1.dto;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "Mark")
public class MarkDTO implements Parcelable {


    @Id
    private long markId;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    private String user;

    @NotNull
    private Date date;

    @NotNull
    private int price;

    @NotNull
    private double lat;

    @NotNull
    private double lng;

    @NotNull
    private String type;

    @NotNull
    private String FIO; //  ФИО пользователя

    @NotNull
    private boolean visible; // Видимость

    public MarkDTO() {
    }


    protected MarkDTO(Parcel in) {
        markId = in.readLong();
        text = in.readString();
        title = in.readString();
        user = in.readString();
        date = new Date(in.readLong());
        price = in.readInt();
        type = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        FIO = in.readString();
        visible = in.readByte() != 0;
    }


    @Generated(hash = 834572465)
    public MarkDTO(long markId, @NotNull String title, @NotNull String text,
            @NotNull String user, @NotNull Date date, int price, double lat,
            double lng, @NotNull String type, @NotNull String FIO,
            boolean visible) {
        this.markId = markId;
        this.title = title;
        this.text = text;
        this.user = user;
        this.date = date;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.FIO = FIO;
        this.visible = visible;
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

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 463915504)
    private transient MarkDTODao myDao;

    public long getMarkId() {
        return markId;
    }

    public void setMarkId(long markId) {
        this.markId = markId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeLong(markId);
        dest.writeString(text);
        dest.writeString(title);
        dest.writeString(user);
        dest.writeLong(date.getTime());
        dest.writeInt(price);
        dest.writeString(type);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(FIO);
        dest.writeByte((byte)(visible?1:0));
    }


    public boolean getVisible() {
        return this.visible;
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 429903719)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMarkDTODao() : null;
    }
}
