package com.commonui.guidepager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonui.R;

public class GuidePager extends BaseIndicatorBanner<Integer, GuidePager>
{
    public GuidePager(Context context) {
        this(context, null, 0);
    }

    public GuidePager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuidePager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBarShowWhenLast(false);
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.base_guide_layout, null);
        ImageView iv = ViewFindUtils.find(inflate, R.id.iv);
        TextView tv_jump = ViewFindUtils.find(inflate, R.id.tv_jump);

        final Integer resId = mDatas.get(position);
        tv_jump.setVisibility(position == mDatas.size() - 1 ? VISIBLE : GONE);

//        填充图片
//        Glide.with(mContext).load(resId).into(iv);

        tv_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJumpClickL != null)
                    onJumpClickL.onJumpClick();
            }
        });

        return inflate;
    }

    private OnJumpClickL onJumpClickL;

    public interface OnJumpClickL {
        void onJumpClick();
    }

    public void setOnJumpClickL(OnJumpClickL onJumpClickL) {
        this.onJumpClickL = onJumpClickL;
    }
}
