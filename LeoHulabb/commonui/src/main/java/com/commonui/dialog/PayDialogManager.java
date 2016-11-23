package com.commonui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonui.R;


/**
 * @desc:         支付结果弹框
 * @author:       Leo
 * @date:         2016/11/23
 */
public class PayDialogManager implements View.OnClickListener
{
    private Dialog payDialog;
    private Context context;

    private final String PAY_SUCCESS_COLOR = "#09bb07";
    private final String PAY_FAILED_COLOR = "#F71C24";

    private final String PAY_SUCCESS = "支付成功";
    private final String PAY_FAILED = "支付失败";

    private final String PAY_SUCCESS_RESULT = "查看订单信息";
    private final String PAY_FAILED_RESULT = "重新支付";

    private ImageView ivResult;         //支付勾叉图标
    private TextView tvResult;          //支付提示文字
    private TextView tvTimecount;       //支付倒计时
    private TextView btnPayResult;      //支付弹窗下一步操作
    private ImageView ivPic;            //支付结果图片
    private ImageView ivCancel;         //弹窗取消按钮

    public PayDialogManager(Context context) {
        createDialog(context, true);
    }

    /**
     * 弹出框构造方法
     * @param context ct
     * @param cancelable <true>点击外部可取消 <false>不可
     */
    public PayDialogManager(Context context, boolean cancelable)
    {
        createDialog(context, cancelable);
    }

    /**
     * 创建弹窗
     * @param context ct
     * @param cancelable   <true>点击外部可取消 <false>不可
     */
    private void createDialog(Context context, boolean cancelable)
    {
        payDialog = new Dialog(context, R.style.progress);
        payDialog.setContentView(R.layout.base_pay_dialog);
        payDialog.setCancelable(cancelable);

        initDialogView();
        setDialogView();
        setListener();
    }

    private void setListener()
    {
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
    }

    private void setDialogView()
    {
        final Window window = payDialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;  // 居中
        window.setAttributes(lp);
    }

    private void initDialogView()
    {
        ivResult = (ImageView) payDialog.findViewById(R.id.iv_result);
        tvResult = (TextView) payDialog.findViewById(R.id.tv_result);
        tvTimecount = (TextView) payDialog.findViewById(R.id.tv_timecount);
        btnPayResult = (TextView) payDialog.findViewById(R.id.btn_pay_result);
        ivPic = (ImageView) payDialog.findViewById(R.id.iv_pic);
        ivCancel = (ImageView) payDialog.findViewById(R.id.iv_cancel);

        final Window window = payDialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;  // 居中
        window.setAttributes(lp);
    }

    //支付成功
    public void showPaySuccess()
    {
        ivResult.setImageResource(R.drawable.icon_right);
        tvResult.setText(PAY_SUCCESS);
        tvTimecount.setVisibility(View.INVISIBLE);
        tvResult.setTextColor(Color.parseColor(PAY_SUCCESS_COLOR));
        ivPic.setImageResource(R.drawable.icon_success);
        btnPayResult.setText(PAY_SUCCESS_RESULT);

        if (payDialog != null)
            payDialog.show();
    }

    //支付失败
    public void showPayFailed()
    {
        ivResult.setImageResource(R.drawable.icon_wrong);
        tvResult.setText(PAY_FAILED);

        tvTimecount.setVisibility(View.VISIBLE);

        tvResult.setTextColor(Color.parseColor(PAY_FAILED_COLOR));
        ivPic.setImageResource(R.drawable.icon_fail);
        btnPayResult.setText(PAY_FAILED_RESULT);

        if (payDialog != null)
            payDialog.show();
    }

    //设置底部按钮点击事件
    public void setPayResultListener(View.OnClickListener listener)
    {
        if (null == listener)
            return;

        btnPayResult.setOnClickListener(listener);
    }

    @Override
    public void onClick(View v) {
        dismissDialog();
    }

    //设置支付失败提示内容
    public void setTvTimecountStr(String content) {

        if (content == null || "".equals(content))
            tvTimecount.setVisibility(View.INVISIBLE);

        SpannableStringBuilder style = new SpannableStringBuilder(content + "后自动取消订单");
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff991c")),
                0, (content != null ? content.length() : 0), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTimecount.setText(style);
    }

    public void dismissDialog()
    {
        if (payDialog != null)
        {
            payDialog.dismiss();
            payDialog = null;
        }
    }
}