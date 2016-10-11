package com.leohulabb.module.login;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.commonui.activity.base.BaseFragment;
import com.commonui.animation.AnimationManager;
import com.commonui.guideview.BGABanner;
import com.commonui.listener.OnClickLoginedListener;
import com.commonui.toast.ToastManager;
import com.leohulabb.R;
import com.leohulabb.testmsp.TestActivityActivity;
import com.leohulabb.testmsp.TestListActivityActivity;
import com.leohulabb.utils.BGABannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2016/7/7.
 */
public class LoginFragment extends BaseFragment
{
    private BGABanner banner;
    private Button button;
    private Button button1;
    private Button button2;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        banner = (BGABanner) findView(R.id.banner);
        button = (Button) findView(R.id.button);
        button1 = (Button) findView(R.id.button1);
        button2 = (Button) findView(R.id.button2);
    }

    @Override
    public void setData() {
        banner.setAdapter(new BGABannerAdapter(context));

        List<String> list = new ArrayList<>();
        list.add("http://picqn.hulabanban.com/Carousel/fa93f5d7560b4308ab9b703f97d2377e.jpg");
        list.add("http://picqn.hulabanban.com/Carousel/1d8aa4166daf4b73be04b336203cbd9a.jpg");
        list.add("http://picqn.hulabanban.com/Carousel/5bfa51d52f9a47ac92ddfb32e92efa99.jpg");
        list.add("http://picqn.hulabanban.com/Carousel/ca4771c0e5a3426281ec35dd3e661123.jpg");
        list.add("http://picqn.hulabanban.com/Carousel/80624ebdca8e4077bf0d1f0cb9b6b602.jpg");
        banner.setData(list, null);
    }

    @Override
    public void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestActivityActivity.class);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestListActivityActivity.class);
            }
        });

//        button.setOnClickListener(new OnClickCustomListener() {
//
//            @Override
//            public boolean isCorrect() {
//                count ++;
//                return false;
//            }
//
//            @Override
//            public void onCorrentClick(View v) {
//                ToastManager.show(context, "click");
//            }
//
//            @Override
//            public void onNoCorrentClick(View v) {
//                if (count % 2 ==0 )
//                    ToastManager.show(context, "don't click");
//                else
//                    ToastManager.show(context, "don't click click click");
//            }
//        });

        button1.setOnClickListener(new OnClickLoginedListener(getActivity()) {
            @Override
            public boolean isLogined(Activity context, View view) {
                return false;
            }

            @Override
            public void onLoginedClick(View v) {
                ToastManager.show(getActivity(), "已登录");
            }

            @Override
            public void onNoLoginedClick(View v) {
                ToastManager.show(getActivity(), "暂未登录");
                startActivity(TestListActivityActivity.class);
            }
        });
//
//        button2.setOnClickListener(new OnClickNetworkListener() {
//            @Override
//            public void onNetworkClick(View v) {
//                startActivity(new Intent(context, TestReboundActivity.class));
////                ToastManager.show(getActivity(), "网络已连接");
//            }
//
//            @Override
//            public void onNoNetworkClick(View v) {
//                ToastManager.show(getActivity(), "网络未连接");
//            }
//        });
    }

    int count;

    @Override
    public void onResume() {
        super.onResume();
        AnimationManager.chainAnim((ViewGroup) rootView);
    }
}