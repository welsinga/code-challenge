package com.guidebook.event;

import android.util.Log;

/**
 * @author Wiebe
 */
public class FetchGuideListErrorEvent {

    private final String _errorMessage;

    public FetchGuideListErrorEvent(String errorMessage) {
        _errorMessage = errorMessage;
        Log.e("FetchGuideList", errorMessage);
    }

    public String getErrorMessage() {
        return _errorMessage;
    }
}
