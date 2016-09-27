package com.commonui.animation;

import android.util.Log;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

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
}
