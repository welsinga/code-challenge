package com.guidebook.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guidebook.Constants;
import com.guidebook.GuidebookApplication;
import com.guidebook.R;
import com.guidebook.data.entity.GuideData;
import com.guidebook.event.FetchGuideListErrorEvent;
import com.guidebook.event.FetchGuideListEvent;
import com.guidebook.service.GuideService;
import com.guidebook.ui.GuideAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //Services
    @Inject
    GuideService _guideService;

    @Inject
    protected EventBus _bus;

    //UI
    @BindView(R.id.gridView)
    protected RecyclerView _gridView;

    @BindView(R.id.mainContainer)
    protected CoordinatorLayout _mainContainer;

    // Values
    @BindInt(R.integer.grid_columns)
    protected int _gridColumns;

    private GuideAdapter _adapter;
    private Snackbar _snackbar;
    private List<GuideData> _dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GuidebookApplication.get(MainActivity.this).component().inject(MainActivity.this);
        _initGridLayout();
        if (savedInstanceState != null) {
            _dataList = Parcels.unwrap(savedInstanceState.getParcelable(Constants.KEYS.LIST));
        } else {
            _guideService.fetchGuideList();
        }
        _updateView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.KEYS.LIST, Parcels.wrap(_dataList));
    }

    /**
     * Initialize GridLayout
     */
    private void _initGridLayout() {
        _gridView.setLayoutManager(new GridLayoutManager(this, _gridColumns));
        _gridView.setHasFixedSize(true);
        _adapter = new GuideAdapter(getBaseContext());
        _gridView.setAdapter(_adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        _bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        _bus.unregister(this);
    }

    /**
     * Handle FetchGuideList event.
     *
     * @param event FetchGuideListEvent
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    @SuppressWarnings("UnusedDeclaration")
    public void onFetchGuideList(FetchGuideListEvent event) {
        _bus.removeStickyEvent(event);
        _dataList = event.getList().getDataList();
        _updateView();
    }

    /**
     * Handle FetchGuideList error event.
     *
     * @param event FetchGuideListErrorEvent
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    @SuppressWarnings("UnusedDeclaration")
    public void onFetchGuideListError(FetchGuideListErrorEvent event) {
        _bus.removeStickyEvent(event);
        _dataList = null;
        _showError();
    }

    /**
     * Update view.
     */
    private void _updateView() {
        if (_dataList != null) {
            _adapter.setList(_dataList);
        }
    }

    /**
     * Shown error when fetching GuideLisT fails
     */
    private void _showError() {
        _snackbar = Snackbar
                .make(_mainContainer, getText(R.string.no_data_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getText(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _snackbar.dismiss();
                        _guideService.fetchGuideList();
                    }
                });
        _snackbar.show();
    }
}
