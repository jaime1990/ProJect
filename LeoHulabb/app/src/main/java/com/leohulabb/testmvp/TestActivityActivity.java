package com.leohulabb.testmvp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.commonui.activity.base.BaseActivity;
import com.commonui.imageloader.ImageLoaderUtils;
import com.commonui.imageview.lean.LeanImageView;
import com.commonui.toast.ToastManager;
import com.commonui.indicator.ColorTransitionPagerTitleView;
import com.commonui.indicator.CommonNavigator;
import com.commonui.indicator.CommonNavigatorAdapter;
import com.commonui.indicator.IPagerIndicator;
import com.commonui.indicator.IPagerTitleView;
import com.commonui.indicator.LinePagerIndicator;
import com.commonui.indicator.MagicIndicator;
import com.commonui.indicator.SimplePagerTitleView;
import com.commonui.indicator.ViewPagerHelper;
import com.commonutils.SizeUtils;
import com.leohulabb.R;
import com.leohulabb.data.TestData;
import com.commonui.activity.LayoutPagerAdapter;
import com.leohulabb.testmvp.contract.TestContract;
import com.leohulabb.testmvp.model.TestModelImpl;
import com.leohulabb.testmvp.presenter.TestPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class TestActivityActivity extends BaseActivity<TestPresenterImpl, TestModelImpl> implements TestContract.View {

    private LeanImageView ivLogo;
    private TextView tvAddress;
    private TextView tvName;

    private String imageStr;

    private MagicIndicator tabIndicator;
    private ViewPager pager;

    List<LinearLayout> mListViews = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.test_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
//        imageStr = getIntent().getStringExtra("Picture");
        imageStr = "http://picqn.hulabanban.com/Carousel/80624ebdca8e4077bf0d1f0cb9b6b602.jpg";
    }

    @Override
    public void initView() {
        ivLogo = (LeanImageView) findViewById(R.id.iv_logo);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvName = (TextView) findViewById(R.id.tv_name);

        tabIndicator = (MagicIndicator) findViewById(R.id.tab_indicator);
        pager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void setData() {
        mPresenter.getDetail();

        ArrayList<String> titi = new ArrayList<>();
        titi.add("星期一");
        titi.add("星期二");
        titi.add("星期三");
        titi.add("星期四");
        titi.add("星期五");
        titi.add("星期六");
        titi.add("星期日");

        for (int i = 0; i < 7; i++) {
            addView(titi.get(i));
        }

        LayoutPagerAdapter adapter = new LayoutPagerAdapter(mListViews);
        pager.setAdapter(adapter);

        initIndicator(titi);
    }

    private void addView(String text)
    {
        final LinearLayout layout = new LinearLayout(context);
        final TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        layout.addView(textView);
        mListViews.add(layout);
    }

    private void initIndicator(final ArrayList<String> titles)
    {
        CommonNavigator navigator = new CommonNavigator(context);
//        navigator.setAdjustMode(true);  // 自适应模式
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles == null ? 0 : titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                SimplePagerTitleView titleView = new ColorTransitionPagerTitleView(context);
                titleView.setText(titles.get(index));
                titleView.setNormalColor(getResources().getColor(R.color.indicator_text_normal));
                titleView.setSelectedColor(getResources().getColor(R.color.indicator_text_selected));
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (index % 3 == 0) {
                            ToastManager.show("暂无项目");
                            return;
                        }

                        pager.setCurrentItem(index);

                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2f));
                indicator.setLineHeight(SizeUtils.dp2px(context, 2));
                indicator.setLineWidth(SizeUtils.dp2px(context, 40));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(getResources().getColor(R.color.base_color));
                return indicator;
            }
        });

        tabIndicator.setNavigator(navigator);
        ViewPagerHelper.bind(tabIndicator, pager);
    }

    @Override
    public void setListener() {
    }

    @Override
    public void setDetail(TestData data) {
        tvAddress.setText(data.getFancierAddress());
        tvName.setText(data.getFancierName());
        ImageLoaderUtils.display(imageStr, ivLogo);
    }

    @Override
    public void noData() {
        ToastManager.show("暂无数据");
        finish();
    }
}