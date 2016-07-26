package com.guidebook.data.entity;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelProperty;

/**
 * Simple POJO representation.
 *
 * @author Wiebe
 */
@Parcel
public class Venue {

    @SerializedName("city")
    @ParcelProperty("city")
    protected String _city;

    @SerializedName("state")
    @ParcelProperty("state")
    protected String _state;


    public String getCity() {
        return _city;
    }

    public String getState() {
        return _state;
    }
}
