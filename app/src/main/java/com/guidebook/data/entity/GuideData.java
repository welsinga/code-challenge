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
public class GuideData {

    @SerializedName("startDate")
    @ParcelProperty("startDate")
    private String _startDate;

    @SerializedName("endDate")
    @ParcelProperty("endDate")
    protected String _endDate;

    @SerializedName("name")
    @ParcelProperty("name")
    protected String _name;

    @SerializedName("url")
    @ParcelProperty("url")
    protected String _url;

    @SerializedName("login_required")
    @ParcelProperty("login_required")
    protected String _loginRequired;

    @SerializedName("venue")
    @ParcelProperty("venue")
    protected Venue _venue;

    @SerializedName("objType")
    @ParcelProperty("objType")
    protected String _type;

    @SerializedName("icon")
    @ParcelProperty("icon")
    protected String _urlIcon;

    public String getStartDate() {
        return _startDate;
    }

    public String getEndDate() {
        return _endDate;
    }

    public String getName() {
        return _name;
    }

    public String getURL() {
        return _url;
    }

    public String getLoginRequired() {
        return _loginRequired;
    }

    public Venue getVenue() {
        return _venue;
    }

    public String getType() {
        return _type;
    }

    public String getURLIcon() {
        return _urlIcon;
    }


}
