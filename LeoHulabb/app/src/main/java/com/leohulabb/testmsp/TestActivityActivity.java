package com.leohulabb.testmsp;

import android.widget.ImageView;
import android.widget.TextView;

import com.commonui.activity.base.BaseActivity;
import com.commonui.toast.ToastManager;
import com.leohulabb.R;
import com.leohulabb.data.TestData;
import com.leohulabb.testmsp.contract.TestContract;
import com.leohulabb.testmsp.model.TestModelImpl;
import com.leohulabb.testmsp.presenter.TestPresenterImpl;
import com.leohulabb.utils.picassoUtils.PicassoImageLoader;

public class TestActivityActivity extends BaseActivity<TestPresenterImpl, TestModelImpl> implements TestContract.View {

    private ImageView ivLogo;
    private TextView tvAddress;
    private TextView tvName;

    private String imageStr;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
        imageStr = getIntent().getStringExtra("Picture");
    }

    @Override
    public void initView() {
        ivLogo = findView(R.id.iv_logo);
        tvAddress = findView(R.id.tv_address);
        tvName = findView(R.id.tv_name);
    }

    @Override
    public void setData() {
        mPresenter.getDetail();
    }

    @Override
    public void setListener() {
    }

    @Override
    public void setDetail(TestData data) {
        tvAddress.setText(data.getFancierAddress());
        tvName.setText(data.getFancierName());
        PicassoImageLoader.getImageLoader().picassoLoader(data.getFancierImage(), ivLogo);
    }

    @Override
    public void noData() {
        ToastManager.show(context, "暂无数据");
        finish();
    }
}