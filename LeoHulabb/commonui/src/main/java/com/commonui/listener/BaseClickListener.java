package com.commonui.listener;

import android.view.View;

/**
 * @desc:   点击事件基础类
 * @author: Leo
 * @date:   2016/10/08
 */
public class BaseClickListener implements View.OnClickListener
{
    @Override
    public void onClick(View v) {
        animateClickView(v);
    }

    private void animateClickView(final View v) {

//        Animation clickAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.base_anim_button);
//        v.startAnimation(clickAnimation);
    }
}
