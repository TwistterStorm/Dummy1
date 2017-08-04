package com.example.sith2.dummy1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sith2 on 8/1/2017.
 */

public class ModelUser implements Parcelable {

    public ModelUser(){}

    String dname;
    String email;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;

    protected ModelUser(Parcel in) {
        dname = in.readString();
        email = in.readString();
        uid = in.readString();
    }

    public static final Creator<ModelUser> CREATOR = new Creator<ModelUser>() {
        @Override
        public ModelUser createFromParcel(Parcel in) {
            return new ModelUser(in);
        }

        @Override
        public ModelUser[] newArray(int size) {
            return new ModelUser[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(dname);
        parcel.writeString(email);
        parcel.writeString(uid);
    }
}
