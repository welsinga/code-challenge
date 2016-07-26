package com.guidebook.event;

import com.guidebook.data.entity.GuideList;

/**
 * @author Wiebe
 */
public class FetchGuideListEvent {

    private final GuideList _list;

    public FetchGuideListEvent(GuideList list) {
        _list = list;
    }

    public GuideList getList() {
        return _list;
    }

}
