package com.commonui.listener;

import android.view.View;

 /**
  * @desc:         自定义点击事件处理
  * @author:       Leo
  * @date:         2016/10/8
  */
public abstract class OnClickCostomListener extends BaseClickListener {

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if (isCorrect()) {
            onCorrentClick(view);
        } else {
            onNoCorrentClick(view);
        }
    }

    /**
     * 判断是否逻辑通过
     * @return
     */
    public abstract boolean isCorrect();

    /**
     * 判断正确之后执行的逻辑请求
     * @param v
     */
    public abstract void onCorrentClick(View v);

    /**
     * 判断失败之后执行的逻辑请求
     * @param v
     */
    public abstract void onNoCorrentClick(View v);
}