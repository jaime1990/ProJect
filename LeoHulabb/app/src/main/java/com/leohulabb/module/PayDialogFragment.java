package com.leohulabb.module;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leohulabb.R;

/**
 * 底部弹窗Fragment
 */
public class PayDialogFragment extends DialogFragment
{
    protected Dialog dialog;
    protected Context context;

    private ImageView ivResult;
    private LinearLayout itemLayout;
    private TextView ivPrice;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = setDialog();
        initView(dialog);
//        setView();
        setListener();
        return dialog;
    }

    protected Dialog setDialog() {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_dialog);

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;  // 居中
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        return dialog;
    }

    protected void initView(Dialog dialog)
    {
        ivResult = (ImageView) dialog.findViewById(R.id.iv_result);
        itemLayout = (LinearLayout) dialog.findViewById(R.id.item_layout);
        ivPrice = (TextView) dialog.findViewById(R.id.iv_price);
        dialog.findViewById(R.id.btn_pay_back).setOnClickListener(null);
    }

    protected void setListener()
    {
    }

    protected void setView()
    {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_venue_order, null);
        inflate.setTag("2");
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("2".equals(v.getTag()))
                    Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
        itemLayout.addView(inflate);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
//                    rePayDetail.startAnimation(slide_left_to_left_in);
//                    rePayDetail.setVisibility(View.VISIBLE);
//                    LinPayWay.startAnimation(slide_left_to_right);
//                    LinPayWay.setVisibility(View.GONE);
                default:
                    break;
            }
        }
    };
}
