package net.eocbox.volleyeocbox_lib.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

/**
 * Created by Bruce_Lan on 15/9/11.
 */
public class NetworkImageView extends ImageView {
    private String mUrl;
    private int mDefaultImageId;
    private int mErrorImageId;
    private ImageLoader mImageLoader;
    private ImageContainer mImageContainer;

    public NetworkImageView(Context context) {
        this(context, (AttributeSet)null);
    }

    public NetworkImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setImageUrl(String url, ImageLoader imageLoader) {
        this.mUrl = url;
        this.mImageLoader = imageLoader;
        this.loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int defaultImage) {
        this.mDefaultImageId = defaultImage;
    }

    public void setErrorImageResId(int errorImage) {
        this.mErrorImageId = errorImage;
    }

    private void loadImageIfNecessary(final boolean isInLayoutPass) {
        int width = this.getWidth();
        int height = this.getHeight();
        boolean isFullyWrapContent = this.getLayoutParams() != null && this.getLayoutParams().height == -2 && this.getLayoutParams().width == -2;
        if(width != 0 || height != 0 || isFullyWrapContent) {
            if(TextUtils.isEmpty(this.mUrl)) {
                if(this.mImageContainer != null) {
                    this.mImageContainer.cancelRequest();
                    this.mImageContainer = null;
                }

                this.setDefaultImageOrNull();
            } else {
                if(this.mImageContainer != null && this.mImageContainer.getRequestUrl() != null) {
                    if(this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                        return;
                    }

                    this.mImageContainer.cancelRequest();
                    this.setDefaultImageOrNull();
                }

                ImageContainer newContainer = this.mImageLoader.get(this.mUrl, new ImageListener() {
                    public void onErrorResponse(VolleyError error) {
                        if(NetworkImageView.this.mErrorImageId != 0) {
                            NetworkImageView.this.setImageResource(NetworkImageView.this.mErrorImageId);
                        }

                    }

                    public void onResponse(final ImageContainer response, boolean isImmediate) {
                        if(isImmediate && isInLayoutPass) {
                            NetworkImageView.this.post(new Runnable() {
                                public void run() {
                                    onResponse(response, false);
                                }
                            });
                        } else {
                            if(response.getBitmap() != null) {
                                NetworkImageView.this.setImageBitmap(response.getBitmap());
                            } else if(NetworkImageView.this.mDefaultImageId != 0) {
                                NetworkImageView.this.setImageResource(NetworkImageView.this.mDefaultImageId);
                            }

                        }
                    }
                });
                this.mImageContainer = newContainer;
            }
        }
    }

    private void setDefaultImageOrNull() {
        if(this.mDefaultImageId != 0) {
            this.setImageResource(this.mDefaultImageId);
        } else {
            this.setImageBitmap((Bitmap)null);
        }

    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.loadImageIfNecessary(true);
    }

    protected void onDetachedFromWindow() {
        if(this.mImageContainer != null) {
            this.mImageContainer.cancelRequest();
            this.setImageBitmap((Bitmap)null);
            this.mImageContainer = null;
        }

        super.onDetachedFromWindow();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.invalidate();
    }
}
