package com.commonui.animation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.commonui.R;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import java.util.List;

/**
 * @desc:   FaceBook弹性动画类
 *         （不兼容安卓4.4.4 目前还未解决）
 * @author: Leo
 * @date:   2016/09/27
 */
public class AnimationManager {

    private AnimationManager() {
        throw new UnsupportedOperationException("error...");
    }

    public static void scaleAnimate(final View view)
    {
        // Create a system to run the physics loop for a set of springs.
        SpringSystem springSystem = SpringSystem.create();

        // Add a spring to the system.
        Spring spring = springSystem.createSpring();

        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(10,5));
        // Add a listener to observe the motion of the spring.
        spring.addListener(new SimpleSpringListener() {

            @Override
            public void onSpringUpdate(Spring spring) {
                // You can observe the updates in the spring
                // state by asking its current value in onSpringUpdate.

                float currentValue = (float) spring.getCurrentValue();
                float head = (float) (((int) (currentValue * 10)) / 10.0);
                float finaValue = currentValue - head + 0.9f;
                float value = currentValue > 0.99f ? currentValue : finaValue;

//                if (currentValue < 0.99f)
//                    value = 1 - currentValue + head;
//                else if (currentValue > 1.1f)
//                    value = 1 + currentValue - head;


                Log.e("head", head + "");
                Log.e("currentValue", currentValue + "");
                Log.e("VALUE", value + "");
                float scaleX = 1f - (value * 0.1f);
                float scaleY = 1f - (value * 0.01f);
                view.setScaleX(scaleX);
                view.setScaleY(scaleX);
            }
        });

        // Set the spring in motion; moving from 0 to 1
        spring.setEndValue(1);
    }

    public static void chainAnim(ViewGroup viewGroup)
    {
        if (null == viewGroup || viewGroup.getChildCount() <= 0)
            return;

        viewGroup.setVisibility(View.VISIBLE);
        SpringChain springChain = SpringChain.create();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = viewGroup.getChildAt(i);
            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationX((float) spring.getCurrentValue());
                }
            });
        }
        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(1080);
        }
        springChain.setControlSpringIndex(0).getControlSpring().setEndValue(0);
    }

    public static void chainAnimOrizontal(ViewGroup viewGroup)
    {
        if (null == viewGroup || viewGroup.getChildCount() <= 0)
            return;

        viewGroup.setVisibility(View.VISIBLE);
        SpringChain springChain = SpringChain.create();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = viewGroup.getChildAt(i);
            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationY((float) -spring.getCurrentValue());
                    Log.e("Animation", spring.getCurrentValue() + "");
                }
            });
        }
        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(1080);
        }
        springChain.setControlSpringIndex(0).getControlSpring().setEndValue(0);
    }

    public static ActSwitchAnimTool largeAnimation(View view, Intent intent)
    {
        return new ActSwitchAnimTool((Activity) view.getContext()).setAnimType(0)
                .target(view)
                .setShrinkBack(true)
                .setmColorStart(Color.parseColor("#FF5777"))
                .setmColorEnd(Color.parseColor("#FF5777"))
                .startActivity(intent, false);
    }

    private static final int DELAY = 50;
    private static int mLastPosition = -1;

    public static void showItemAnim(final View view, final int position)
    {
        final Context context = view.getContext();
        if (position > mLastPosition) {
            view.setAlpha(0);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) {
                            view.setAlpha(1);
                        }

                        @Override public void onAnimationEnd(Animation animation) {}

                        @Override public void onAnimationRepeat(Animation animation) {}
                    });
                    view.startAnimation(animation);
                }
            }, DELAY * position);
            mLastPosition = position;
        }
    }
}
