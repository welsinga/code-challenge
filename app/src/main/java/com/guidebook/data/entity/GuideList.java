package com.guidebook.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Simple POJO representation.
 *
 * @author Wiebe
 */
public class GuideList {

    @SerializedName("total")
    private String _total;

    @SerializedName("data")
    private List<GuideData> _dataList;


    public String getTotal() {
        return _total;
    }

    public List<GuideData> getDataList() {
        return _dataList;
    }
}
