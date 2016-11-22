package com.leohulabb.module;

import android.view.View;

import com.commonui.activity.base.BaseFragment;
import com.commonui.button.StateButton;
import com.leohulabb.R;

/**
 * Created by Leo on 2016/7/7.
 */
public class ButtonFragment extends BaseFragment
{

    StateButton text;

    StateButton background;

    StateButton radius;

    StateButton stroke;

    StateButton dash;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_button;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        //设置不同状态下文字变色
        text = (StateButton) findView(R.id.text_test);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(PermissionTestActivity.class);
            }
        });

        //最常用的设置不同背景
        background = (StateButton) findView(R.id.background_test);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestReboundActivity.class);
            }
        });

        //设置四个角不同的圆角
        radius = (StateButton) findView(R.id.different_radius_test);
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});


        //设置不同状态下边框颜色，宽度
        stroke = (StateButton) findView(R.id.stroke_test);
        stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stroke.setEnabled(false);
            }
        });

        //设置间断
        dash = (StateButton) findView(R.id.dash_test);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dash.setEnabled(false);
            }
        });
    }

    @Override
    public void setData() {

    }

    @Override
    public void setListener() {

    }
}