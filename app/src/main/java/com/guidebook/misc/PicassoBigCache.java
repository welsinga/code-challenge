package com.guidebook.misc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.StatFs;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import com.squareup.picasso.UrlConnectionDownloader;

import java.io.File;

/**
 * Code from: https://gist.github.com/fada21/10655652
 * only modified some values
 */
public enum PicassoBigCache {

    INSTANCE;

    private static final String BIG_CACHE_PATH = "picasso-big-cache";
    private static final int MIN_DISK_CACHE_SIZE = 10 * 1024 * 1024;       // 10 MB
    private static final int MAX_DISK_CACHE_SIZE = 100 * 1024 * 1024;      // 100 MB

    private static final float MAX_AVAILABLE_SPACE_USE_FRACTION = 0.3f;

    private Picasso picassoInstance;

    private void _init(Context ctx) {
        if (ctx == null) {
            throw new IllegalStateException("Must provide context to init PicassoBigCache!"); // fail fast
        }
        ctx = ctx.getApplicationContext(); // need application context - activity's context could cause harm
        Builder builder = new Builder(ctx);
        builder.downloader(createBigCacheDownloader(ctx));
        picassoInstance = builder.build();
    }

    public Picasso getPicassoBigCache(Context ctx) {
        if (picassoInstance == null) {
            synchronized (INSTANCE) {
                if (picassoInstance == null)
                    _init(ctx);
            }
        }
        return picassoInstance;
    }

    static Downloader createBigCacheDownloader(Context ctx) {
        try {
            Class.forName("com.squareup.okhttp.OkHttpClient");
            File cacheDir = _createDefaultCacheDir(ctx, BIG_CACHE_PATH);
            long cacheSize = _calculateDiskCacheSize(cacheDir);
            OkHttpDownloader downloader = new OkHttpDownloader(cacheDir, cacheSize);
            return downloader;
        } catch (ClassNotFoundException e) {
            return new UrlConnectionDownloader(ctx);
        }
    }

    private static File _createDefaultCacheDir(Context context, String path) {
        File cacheDir = context.getApplicationContext().getExternalCacheDir();
        if (cacheDir == null)
            cacheDir = context.getApplicationContext().getCacheDir();
        File cache = new File(cacheDir, path);
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }

    /**
     * Calculates bonded min max cache size. Min value is {@link #MIN_DISK_CACHE_SIZE}
     *
     * @param dir cache dir
     * @return disk space in bytes
     */

    private static long _calculateDiskCacheSize(File dir) {
        long size = Math.min(_calculateAvailableCacheSize(dir), MAX_DISK_CACHE_SIZE);
        return Math.max(size, MIN_DISK_CACHE_SIZE);
    }

    /**
     * Calculates minimum of available or total fraction of disk space
     *
     * @param dir
     * @return space in bytes
     */
    @SuppressLint("NewApi")
    private static long _calculateAvailableCacheSize(File dir) {
        long size = 0;
        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            int sdkInt = Build.VERSION.SDK_INT;
            long availableBytes;
            if (sdkInt < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                int blockSize = statFs.getBlockSize();
                availableBytes = ((long) statFs.getAvailableBlocks()) * blockSize;
            } else {
                availableBytes = statFs.getAvailableBytes();
            }
            // Target 40% of the available space
            size = (long) (availableBytes * MAX_AVAILABLE_SPACE_USE_FRACTION);
        } catch (IllegalArgumentException ignored) {
            // ignored
        }
        return size;
    }

}