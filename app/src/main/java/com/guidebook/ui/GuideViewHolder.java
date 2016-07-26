package com.guidebook.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guidebook.GuidebookApplication;
import com.guidebook.R;
import com.guidebook.data.entity.GuideData;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wiebe
 */

public class GuideViewHolder extends RecyclerView.ViewHolder {

    //Services
    @Inject
    protected Picasso _picasso;

    //UI
    @BindView(R.id.image)
    protected ImageView _image;

    @BindView(R.id.name)
    protected TextView _name;

    @BindView(R.id.date)
    protected TextView _date;

    public GuideViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(GuideViewHolder.this, itemView);
        GuidebookApplication.get(context).component().inject(GuideViewHolder.this);
    }

    public void setViewData(GuideData guideData) {
        if (!TextUtils.isEmpty(guideData.getURLIcon())) {
            _picasso.load(guideData.getURLIcon()).into(_image);
        }
        _name.setText(guideData.getName());
        _date.setText(guideData.getStartDate() + " - " + guideData.getEndDate());
    }
}
