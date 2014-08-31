/*
 * Copyright (C) 2014 Charalampakis Basilis - Blur ActionBarDrawerToggle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dudutech.duduslim.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class BlurActionBarDrawerToggle extends ActionBarDrawerToggle {

    private Context context;

    /** the layout that we take snapshot */
    private View mLayout;

    /** an imageview to display the blurred snapshot/bitmap */
    private ImageView mBlurredImageView;

    /** default blur radius */
    private int radius = 5;

    /** render indicator */
    private boolean render = true;

    /** for "fake" sliding detection */
    private boolean isOpening = false;

    public BlurActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout,
                                     int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.context = activity.getBaseContext();

    }

    public BlurActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout,
                                     int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes, View layout) {
        super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.context = activity.getBaseContext();
        init(layout, radius);
    }

    public BlurActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout,
                                     int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes, View layout, int radius) {
        super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.context = activity.getBaseContext();
        init(layout, radius);
    }


    public void init(final View layout) {
        init(layout, radius);
    }

    /**
     * This function must be invoked if "default" (1st) constructor is called
     * or if you want to change the blurred layout.
     * <p/>
     * We make a fake ImageView with width and height MATCH_PARENT.
     * This ImageView will host the blurred snapshot/bitmap.
     *
     * @param layout A {@link android.widget.RelativeLayout} to take snapshot of it.
     * @param radius Blur radius
     */
    public void init(final View layout, int radius) {
        this.mLayout = layout;
        this.radius = radius;


        mBlurredImageView = new ImageView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        mBlurredImageView.setLayoutParams(params);
        mBlurredImageView.setClickable(false);
        mBlurredImageView.setVisibility(View.GONE);
        ((RelativeLayout) this.mLayout).addView(mBlurredImageView);
    }


    @Override
    public void onDrawerSlide(final View drawerView, final float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);

        //must check this for "fake" sliding..
        if (slideOffset == 0.f)
            isOpening = false;
        else
            isOpening = true;

        render();
        setAlpha(mBlurredImageView, slideOffset, 100);
    }


    @Override
    public void onDrawerClosed(View view) {
        render = true;
        mBlurredImageView.setVisibility(View.GONE);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        super.onDrawerStateChanged(newState);

        // "fake" sliding detection
        if (newState == DrawerLayout.STATE_IDLE
                && !isOpening) {

            handleRecycle();
        }
    }

    /**
     * Snapshots the specified layout and scale it using scaleBitmap() function
     * then we blur the scaled bitmap with the preferred blur radius.
     * Finally, we post it to our fake {@link android.widget.ImageView}.
     */

    private void render() {

        if (render) {
            render = false;

            Bitmap bitmap = loadBitmapFromView(mLayout);
            bitmap = scaleBitmap(bitmap);
            bitmap = Blur.fastblur(context, bitmap, radius);

            mBlurredImageView.setVisibility(View.VISIBLE);
            mBlurredImageView.setImageBitmap(bitmap);
        }

    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private void setAlpha(View view, float alpha, long durationMillis) {
        if (Build.VERSION.SDK_INT < 11) {
            final AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        } else {
            view.setAlpha(alpha);
        }
    }


    private Bitmap scaleBitmap(Bitmap myBitmap) {

        //this must be changed
        //depends on device screen density
        final int maxSize = 250;

        int outWidth;
        int outHeight;
        int inWidth = myBitmap.getWidth();
        int inHeight = myBitmap.getHeight();

        if (inWidth > inHeight) {
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        return Bitmap.createScaledBitmap(myBitmap, outWidth, outHeight, false);
    }


    private Bitmap loadBitmapFromView(View mView) {
        Bitmap b = Bitmap.createBitmap(
                mView.getWidth(),
                mView.getHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(b);

        // With the following, screen blinks
        //v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);

        mView.draw(c);


        return b;
    }

    private void handleRecycle() {
        Drawable drawable = mBlurredImageView.getDrawable();

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
            Bitmap bitmap = bitmapDrawable.getBitmap();

            if (bitmap != null)
                bitmap.recycle();

            mBlurredImageView.setImageBitmap(null);
        }

        render = true;
    }
}