package com.leohulabb.testmvp;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.commonui.animation.AnimationManager;
import com.commonui.listview.BaseQuickAdapter;
import com.commonui.listview.BaseViewHolder;
import com.leohulabb.R;
import com.leohulabb.data.TestListData;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ListViewAdapter extends BaseQuickAdapter<TestListData> {
    public ListViewAdapter(int layoutResId, List<TestListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestListData item) {
        helper.setText(R.id.listview_tv_title,item.getCnName()).setText(R.id.listview_tv_content,"热度:"+item.getHits());
        Glide.with(mContext)
                .load(item.getLogo().getPictureUrl())
                .crossFade()
                .placeholder(R.mipmap.bg_square_ing)
                .into((ImageView) helper.getView(R.id.listview_image_url));

        AnimationManager.showItemAnim(helper.convertView, helper.getLayoutPosition());
    }
}
