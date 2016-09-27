package com.leohulabb.module.login;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.commonui.animation.AnimationManager;
import com.leohulabb.R;
import com.leohulabb.module.base.BaseFragment;
import com.leohulabb.utils.picassoUtils.PicassoUtils;

/**
 * Created by Leo on 2016/7/7.
 */
public class LoginFragment extends BaseFragment
{
    String url = "https://img3.doubanio.com/img/celebrity/large/50433.jpg";
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {

        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
        Button button = (Button) rootView.findViewById(R.id.btn_login);

        AnimationManager.scaleAnimate(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "clicked me", Toast.LENGTH_SHORT).show();
            }
        });
        PicassoUtils.get().picassoLoader(R.mipmap.bg_test, imageView);
    }

}