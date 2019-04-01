// "(c) Walgreen Co. All rights reserved"

package com.android.nytimes;

import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(RobolectricCommonBindingGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/test/AndroidManifest.xml", packageName = "com.wag.horizon.android.mystore")
public class BaseTest {
}
