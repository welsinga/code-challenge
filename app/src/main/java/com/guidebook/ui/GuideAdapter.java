package com.guidebook.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.guidebook.R;
import com.guidebook.data.entity.GuideData;

import java.util.List;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} for showing {@link GuideData} items.
 *
 * @author Wiebe
 */
public class GuideAdapter extends RecyclerView.Adapter<GuideViewHolder> {

    private final LayoutInflater _layoutInflater;
    private List<GuideData> _list;
    private Context _context;

    /**
     * Default constructor.
     *
     * @param context {@link Context}
     */
    public GuideAdapter(Context context) {
        _context = context;
        _layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GuideViewHolder(_context, _layoutInflater.inflate(R.layout.guide_list_item_, parent, false));
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {
        holder.setViewData(_list.get(position));
    }

    @Override
    public int getItemCount() {
        return _list == null ? 0 : _list.size();
    }

    /**
     * Set the list of {@link GuideData}.
     * @param list {@link List} of {@link GuideData}
     */
    public void setList(List<GuideData> list) {
        _list = list;
        notifyDataSetChanged();
    }
}
