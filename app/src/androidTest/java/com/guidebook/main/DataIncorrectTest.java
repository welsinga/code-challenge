package com.guidebook.main;


import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.guidebook.Constants;
import com.guidebook.R;
import com.guidebook.activity.MainActivity;
import com.guidebook.util.RestServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DataIncorrectTest extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<MainActivity> _activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    private MockWebServer _mockServer;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        _mockServer = new MockWebServer();
        _mockServer.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Constants.BASE_URL = _mockServer.url("/").toString();
    }

    @Test
    public void testRetryMessageIsShown() throws Exception {
        String fileName = "404_not_found.json";

        _mockServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        _activityRule.launchActivity(intent);

        onView(ViewMatchers.withText(R.string.no_data_error)).check(matches(isDisplayed()));
        onView(withText(R.string.retry)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        _mockServer.shutdown();
    }
}
