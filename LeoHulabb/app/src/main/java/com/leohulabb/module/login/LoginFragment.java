package com.leohulabb.module.login;

import android.widget.ImageView;

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

        PicassoUtils.get().picassoLoader(R.mipmap.bg_test, imageView);
//        getPicasso().with(context).load(R.mipmap.bg_test).into(imageView);
    }

}