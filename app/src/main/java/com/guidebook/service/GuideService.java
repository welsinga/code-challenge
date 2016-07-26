package com.guidebook.service;

import com.guidebook.api.ApiClient;
import com.guidebook.data.entity.GuideList;
import com.guidebook.event.FetchGuideListErrorEvent;
import com.guidebook.event.FetchGuideListEvent;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Service responsible for Guide related content.
 *
 * @author Wiebe
 */
public class GuideService {

    private final ApiClient _apiClient;
    private final EventBus _bus;

    /**
     * Default constructor.
     *
     * @param apiClient {@link ApiClient}
     * @param bus {@link EventBus}
     */
    public GuideService(ApiClient apiClient, EventBus bus) {
        _apiClient = apiClient;
        _bus = bus;
    }

    /**
     * Fetch the Guide list
     */
    public void fetchGuideList() {
        _apiClient.getGuideList().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GuideList>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {
                        _bus.postSticky(new FetchGuideListErrorEvent(e.getMessage()));
                    }

                    @Override
                    public void onNext(GuideList guideList) {
                        _bus.postSticky(new FetchGuideListEvent(guideList));
                    }
                });
    }
}
