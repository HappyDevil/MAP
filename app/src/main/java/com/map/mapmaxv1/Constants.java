package com.map.mapmaxv1;

import java.util.Date;

public class Constants {

    public static class URL
    {
        public static final String Host="http://172.18.11.62:8080";
        public static final String Regist=Host+"/user/register";
        public static final String GetMarks=Host+"/mark/getAll";
        public static final String SaveMarks=Host+"/mark/save";
        public static final String Auth=Host+"/user/auth";
    }
    public static class MARK_DB
    {
        public static final int DATABASE_VERSION=1;
        public static final String DATABASE_NAME="Mark";

        public static final String MARK_ID="id";
        public static final String MARK_TEXT="text";
        public static final String MARK_USER="userName";
        public static final String MARK_DATE="datemark";
        public static final String MARK_PRICE="price";
        public static final String MARK_LAT="lat";
        public static final String MARK_LNG="lng";
        public static final String MARK_TYPE="type";
        public static final String MARK_FIO="FIO";
        public static final String MARK_VISIBLE="visible";
        public static final String MARK_VERSION="version";
    }
    public static class JOB_TYPES
    {
        public static final String[] list = {"A", "B", "C", "Другое..."};
    }
}