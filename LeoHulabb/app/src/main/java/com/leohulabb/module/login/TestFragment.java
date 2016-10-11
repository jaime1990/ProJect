package com.leohulabb.module.login;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.commonui.activity.base.BaseFragment;
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
    }

    @Override
    public void setData() {

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