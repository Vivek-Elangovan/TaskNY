package com.android.nytimes;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

public class WebPageActivityTest extends BaseTest {

    private WebPageActivity webPageActivity;

    @Before
    public void setupResponse() {
        webPageActivity = Robolectric.buildActivity(WebPageActivity.class)
                .withIntent(getIntent()).create().get();
    }

    @Test
    public void testLoadArticle() {
        webPageActivity.onCreateViewModel();
    }


    private Intent getIntent() {
        Intent intent = new Intent(WebPageActivity.class.getName());
        intent.putExtra("URL", "https://developer.nytimes.com/my-apps");
        return intent;
    }
}
