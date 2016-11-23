package com.leohulabb.module;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.commonui.activity.LayoutPagerAdapter;
import com.commonui.activity.base.BaseFragment;
import com.commonui.indicator.ColorTransitionPagerTitleView;
import com.commonui.indicator.CommonNavigator;
import com.commonui.indicator.CommonNavigatorAdapter;
import com.commonui.indicator.IPagerIndicator;
import com.commonui.indicator.IPagerTitleView;
import com.commonui.indicator.LinePagerIndicator;
import com.commonui.indicator.MagicIndicator;
import com.commonui.indicator.SimplePagerTitleView;
import com.commonui.indicator.ViewPagerHelper;
import com.commonui.toast.ToastManager;
import com.commonutils.SizeUtils;
import com.leohulabb.R;
import com.leohulabb.module.testpicasso.TestPicassoItemAdapter;
import com.leohulabb.module.testpicasso.TestPicassoItemAdapter.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2016/7/7.
 */
public class TestFragment extends BaseFragment
{
    private MagicIndicator tabIndicator;
    private ViewPager pager;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_picasso_test;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (setDatas() != null) {
            List<Type> types = setDatas();
            recyclerView.setAdapter(new TestPicassoItemAdapter(types));
        }

        tabIndicator = (MagicIndicator) findView(R.id.tab_indicator);
        pager = (ViewPager) findView(R.id.viewpager);
    }

    List<LinearLayout> mListViews = new ArrayList<>();

    @Override
    public void setData() {
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
        final TextView textView = new TextView(getActivity());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        layout.addView(textView);
        mListViews.add(layout);
    }

    private void initIndicator(final ArrayList<String> titles)
    {
        CommonNavigator indicator = new CommonNavigator(getActivity());
//        indicator.setAdjustMode(true);  // 自适应模式
        indicator.setAdapter(new CommonNavigatorAdapter() {
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
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.RED);
                return indicator;
            }
        });

        tabIndicator.setNavigator(indicator);
        ViewPagerHelper.bind(tabIndicator, pager);
    }

    private List<Type> setDatas()
    {
        List<Type> dataSet = new ArrayList<>();
        dataSet.add(Type.BLUR);
//        dataSet.add(Type.CROP);
        dataSet.add(Type.CROPCIRCLE);
//        dataSet.add(Type.CROPSQUARE);
        dataSet.add(Type.GRAYSCALE);
        dataSet.add(Type.ROUNDEDCORNERS);

        if (dataSet != null && dataSet.size() > 0 )
            return dataSet;

        return null;
    }

    @Override
    public void setListener() {

    }
}