package com.guidebook.api;

import com.guidebook.data.entity.GuideList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Client responsible for API calls.
 *
 * @author Wiebe
 */
public interface ApiClient {

    @GET("/service/v2/upcomingGuides")
    Observable<GuideList> getGuideList();
}
