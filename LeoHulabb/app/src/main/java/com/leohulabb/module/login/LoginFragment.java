package com.leohulabb.module.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.commonui.animation.AnimationManager;
import com.commonui.navigation.NavigationBar;
import com.commonui.navigation.WidgeButton;
import com.leohulabb.R;
import com.leohulabb.module.base.BaseFragment;

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
//        BGABanner banner = (BGABanner) rootView.findViewById(R.id.banner);
//        banner.setAdapter(new BGABannerAdapter(getActivity()));
//        List<String> list = new ArrayList<>();
//        list.add("http://picqn.hulabanban.com/Carousel/fa93f5d7560b4308ab9b703f97d2377e.jpg");
//        list.add("http://picqn.hulabanban.com/Carousel/1d8aa4166daf4b73be04b336203cbd9a.jpg");
//        list.add("http://picqn.hulabanban.com/Carousel/5bfa51d52f9a47ac92ddfb32e92efa99.jpg");
//        list.add("http://picqn.hulabanban.com/Carousel/ca4771c0e5a3426281ec35dd3e661123.jpg");
//        list.add("http://picqn.hulabanban.com/Carousel/80624ebdca8e4077bf0d1f0cb9b6b602.jpg");
//        banner.setData(list, null);

        WidgeButton[] widgeButtons = new WidgeButton[] {
                new WidgeButton(context, R.string.base_back),
                new WidgeButton(context, R.string.base_confirm),
                new WidgeButton(context, R.string.base_back) };

        NavigationBar navigationBar = (NavigationBar) rootView.findViewById(R.id.navigationBar);
        navigationBar.setAppWidgeTitle("首页");
        navigationBar.setLeftMenus(widgeButtons);
//        navigationBar.setRightContent(R.string.base_cancel);
        navigationBar.setRightMenu(new WidgeButton(context, R.string.base_back));

        Button button = (Button) rootView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GuideActivity.class);

                AnimationManager.largeAnimation((Activity) context, v, intent).setAnimType(0).build();
            }
        });
    }

}