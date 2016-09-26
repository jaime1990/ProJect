package com.leohulabb.module.login;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leohulabb.R;
import com.leohulabb.module.base.BaseFragment;
import com.leohulabb.module.testpicasso.TestPicassoItemAdapter;
import com.leohulabb.module.testpicasso.TestPicassoItemAdapter.Type;
import com.leohulabb.utils.picassoUtils.PicassoPauseOnScrollListener;

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
    protected void onInitView() {

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (setData() != null) {
            List<Type> types = setData();
            recyclerView.setAdapter(new TestPicassoItemAdapter(types));
        }
    }

    private List<Type> setData()
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
}