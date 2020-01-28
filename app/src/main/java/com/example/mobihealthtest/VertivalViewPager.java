package com.example.mobihealthtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.viewpager.widget.ViewPager;

public class VertivalViewPager extends ViewPager {


    float x = 0;
    float mStartDragX = 0;
    private static final float SWIPE_X_MIN_THRESHOLD = 20; // Decide this magical number as per your requirement


    public VertivalViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public VertivalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * @return {@code false} since a vertical view pager can never be scrolled horizontally
     */
    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }
    /**
     * @return {@code true} iff a normal view pager would support horizontal scrolling at this time
     */
    @Override
    public boolean canScrollVertically(int direction) {
        return super.canScrollHorizontally(direction);
    }
    private void init() {
        // Make page transit vertical
        setPageTransformer(true, new VerticalPageTransformer());
        // Get rid of the overscroll drawing that happens on the left and right (the ripple)
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getAdapter() != null) {
            //Log.i("VerticalViewPager", "onTouchEvent " + x + " : " + mStartDragX + " : " + (mStartDragX - x));
            if (getCurrentItem() >= 0 || getCurrentItem() < getAdapter().getCount()) {
                swapXY(event);
                final int action = event.getAction();
                switch (action & MotionEventCompat.ACTION_MASK) {
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        mStartDragX = event.getX();
                        if (x < mStartDragX
                                && (mStartDragX - x > SWIPE_X_MIN_THRESHOLD)
                                && getCurrentItem() > 0) {
                            //Log.i("VerticalViewPager", "down " + x + " : " + mStartDragX + " : " + (mStartDragX - x));
                            setCurrentItem(getCurrentItem() - 1, true);
                            return true;
                        } else if (x > mStartDragX
                                && (x - mStartDragX > SWIPE_X_MIN_THRESHOLD)
                                && getCurrentItem() < getAdapter().getCount() - 1) {
                            //Log.i("VerticalViewPager", "up " + x + " : " + mStartDragX + " : " + (x - mStartDragX));
                            mStartDragX = 0;
                            setCurrentItem(getCurrentItem() + 1, true);
                            return true;
                        } else {
                            //Log.i("VerticalViewPager", "stable " + x + " : " + mStartDragX + " : " + (x - mStartDragX));
                        }
                        break;
                }
            } else {
                mStartDragX = 0;
            }
            swapXY(event);
            return super.onTouchEvent(swapXY(event));
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = super.onInterceptTouchEvent(swapXY(event));
        switch (event.getAction() & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                break;
        }

        swapXY(event); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    private class VerticalPageTransformer implements PageTransformer {
        public void transformPage(View view, float position) {
            final int pageWidth = view.getWidth();
            final int pageHeight = view.getHeight();
            if (position < -1) {
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 1) {
                view.setAlpha(1);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // set Y position to swipe in from top
                float yPosition = position * pageHeight;
                view.setTranslationY(yPosition);
            } else {
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
