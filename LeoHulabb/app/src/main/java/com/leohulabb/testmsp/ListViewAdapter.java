package com.leohulabb.testmsp;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.commonui.listview.BaseQuickAdapter;
import com.commonui.listview.BaseViewHolder;
import com.leohulabb.R;
import com.leohulabb.data.TestData;
import com.leohulabb.data.UniversityListDto;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ListViewAdapter extends BaseQuickAdapter<UniversityListDto> {
    public ListViewAdapter(int layoutResId, List<UniversityListDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UniversityListDto item) {
        helper.setText(R.id.listview_tv_title,item.getCnName()).setText(R.id.listview_tv_content,"热度:"+item.getHits());
        Glide.with(mContext)
                .load(item.getLogo().getPictureUrl())
                .crossFade()
                .placeholder(R.mipmap.bg_square_ing)
                .into((ImageView) helper.getView(R.id.listview_image_url));
    }
}
