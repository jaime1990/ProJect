package com.commonui.activity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by Leo on 2016/9/10.
 */
public class LayoutPagerAdapter extends PagerAdapter
{
    private List<LinearLayout> mViews;

    public LayoutPagerAdapter(List<LinearLayout> mViews)
    {
        this.mViews = mViews;
    }

    @Override
    public int getCount()
    {
      if(mViews != null){
          return mViews.size();
      }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == (object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
       container.removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(mViews.get(position), 0);
        return mViews.get(position);
    }
}
