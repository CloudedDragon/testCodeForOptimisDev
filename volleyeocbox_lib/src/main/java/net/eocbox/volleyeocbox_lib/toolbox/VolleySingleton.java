package net.eocbox.volleyeocbox_lib.toolbox;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Bruce_Lan on 15/9/11.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance = null;

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    private VolleySingleton(Context context)
    {
       mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack());

        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
        // Use 1/5th of the available memory for this memory cache.
        int cacheSize = 1024 * 1024 * memClass / 5;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));

//		mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache()
//		{
//			private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
//
//			public void putBitmap(String url, Bitmap bitmap)
//			{
//
//				mCache.put(url, bitmap);
//			}
//
//			public Bitmap getBitmap(String url)
//			{
//
//				return mCache.get(url);
//			}
//		});
    }

    public static VolleySingleton getInstance(Context context)
    {

        if (mInstance == null)
        {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {

        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader()
    {

        return this.mImageLoader;
    }
}
