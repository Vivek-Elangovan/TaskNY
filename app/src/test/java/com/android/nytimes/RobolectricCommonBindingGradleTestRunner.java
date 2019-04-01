// "(c) Walgreen Co. All rights reserved"

package com.android.nytimes;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.util.Logger;
import org.robolectric.util.ReflectionHelpers;

/**
 * Test runner customized for running unit tests either through the Gradle CLI or
 * Android Studio. The runner uses the build type and build flavor to compute the
 * resource, asset, and AndroidManifest paths.
 * <p>
 * This test runner requires that you set the 'constants' field on the @Config
 * annotation (or the org.robolectric.Config.properties file) for your tests.
 */
public class RobolectricCommonBindingGradleTestRunner extends RobolectricTestRunner {
    private static final String BUILD_OUTPUT = "build/intermediates";

    /**
     * Constructor
     *
     * @param klass Class
     * @throws InitializationError throws InitializationError exception
     */
    public RobolectricCommonBindingGradleTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {

        final String type = getType(config);
        final String flavor = getFlavor(config);
        final String packageName = getPackageName(config);

        FileFsFile res;
        final FileFsFile assets;
        FileFsFile manifest;

        // res/merged added in Android Gradle plugin 1.3-beta1
        if (FileFsFile.from(BUILD_OUTPUT, "data-binding-layout-out").exists()) {
            // Android gradle plugin 1.5.0+ puts the merged layouts in data-binding-layout-out.
            res = FileFsFile.from(BUILD_OUTPUT, "data-binding-layout-out", flavor, type);
        } else if (FileFsFile.from(BUILD_OUTPUT, "res", "merged").exists()) {
            res = FileFsFile.from(BUILD_OUTPUT, "res", "merged", flavor, type);
        } else if (FileFsFile.from(BUILD_OUTPUT, "res").exists()) {
            res = FileFsFile.from(BUILD_OUTPUT, "res", flavor, type);
        } else {
            res = FileFsFile.from(BUILD_OUTPUT, "bundles", flavor, type, "res");
        }

//        res = FileFsFile.from(BUILD_OUTPUT, "data-binding-layout-out", flavor, type);

        if (FileFsFile.from(BUILD_OUTPUT, "assets").exists()) {
            assets = FileFsFile.from(BUILD_OUTPUT, "assets", flavor, type);
        } else {
            assets = FileFsFile.from(BUILD_OUTPUT, "bundles", flavor, type, "assets");
        }

        if (FileFsFile.from(BUILD_OUTPUT, "manifests").exists()) {
            manifest = FileFsFile.from(BUILD_OUTPUT, "manifests", "full", flavor, type, "AndroidManifest.xml");
        } else {
            manifest = FileFsFile.from(BUILD_OUTPUT, "bundles", flavor, type, "AndroidManifest.xml");
        }

//        manifest = FileFsFile.from(config.manifest());

        Logger.debug("Robolectric assets directory: " + assets.getPath());
        Logger.debug("   Robolectric res directory: " + res.getPath());
        Logger.debug("   Robolectric manifest path: " + manifest.getPath());
        Logger.debug("    Robolectric package name: " + packageName);
        return new AndroidManifest(manifest, res, assets, packageName);
    }

    private static String getType(Config config) {
        try {
            return ReflectionHelpers.getStaticField(config.constants(), "BUILD_TYPE");
        } catch (Exception e) {
            Logger.error("Exception", e);
            return null;
        }
    }

    private static String getFlavor(Config config) {
        //ReflectionHelpers.getStaticField(config.constants(), "FLAVOR") return empty
        return BuildConfig.FLAVOR;
    }

    private static String getPackageName(Config config) {
        try {
            final String packageName = config.packageName();
            if (!packageName.isEmpty()) {
                return packageName;
            } else {
                return ReflectionHelpers.getStaticField(config.constants(), "APPLICATION_ID");
            }
        } catch (Exception e) {
            Logger.error("Exception", e);
            return null;
        }
    }
}
