package com.leohulabb.testmsp;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.commonui.listview.BaseQuickAdapter;
import com.commonui.listview.BaseViewHolder;
import com.leohulabb.R;
import com.leohulabb.data.TestData;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ListViewAdapter extends BaseQuickAdapter<TestData> {
    public ListViewAdapter(int layoutResId, List<TestData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestData item) {
        helper.setText(R.id.listview_tv_title,item.getFancierName()).setText(R.id.listview_tv_content,"热度:"+item.getFancierBattleId());
        Glide.with(mContext)
                .load(item.getFancierImage())
                .crossFade()
                .placeholder(R.mipmap.bg_square_ing)
                .into((ImageView) helper.getView(R.id.listview_image_url));
    }
}
