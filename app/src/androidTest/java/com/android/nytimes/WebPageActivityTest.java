package com.android.nytimes;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebPageActivityTest {
    /**
     * The M activity test rule.
     */
    @Rule
    public ActivityTestRule<WebPageActivity> mActivityTestRule = new ActivityTestRule<>(WebPageActivity.class);

    /**
     * About activity test.
     */
    @Test
    public void aboutActivityTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(mActivityTestRule);
    }
}
