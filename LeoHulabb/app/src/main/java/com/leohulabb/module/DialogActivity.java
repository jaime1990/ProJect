package com.leohulabb.module;

import android.view.View;

import com.commonui.activity.base.BaseActivity;
import com.commonui.dialog.DialogUIItemListener;
import com.commonui.dialog.DialogUIListener;
import com.commonui.dialog.DialogUIUtils;
import com.leohulabb.R;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        setView();
    }

    @Override
    public void setData() {
        DialogUIUtils.init(context);
    }

    @Override
    public void setListener() {

    }

    private void setView() {
        findViewById(R.id.btn_toast).setOnClickListener(this);
        findViewById(R.id.btn_loading_vertical).setOnClickListener(this);
        findViewById(R.id.btn_loading_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_md_loading_vertical).setOnClickListener(this);
        findViewById(R.id.btn_md_loading_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_md_alert).setOnClickListener(this);
        findViewById(R.id.btn_tie_alert).setOnClickListener(this);
        findViewById(R.id.btn_alert_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_alert_vertical).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet_cancel).setOnClickListener(this);
        findViewById(R.id.btn_center_sheet).setOnClickListener(this);
        findViewById(R.id.btn_alert_input).setOnClickListener(this);
        findViewById(R.id.btn_alert_multichoose).setOnClickListener(this);
        findViewById(R.id.btn_alert_singlechoose).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.btn_md_bottom_vertical).setOnClickListener(this);
        findViewById(R.id.btn_md_bottom_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_custom_alert).setOnClickListener(this);
    }

    String msg = "别总是来日方长，这世上挥手之间的都是人走茶凉。";

    public void showToast(CharSequence msg) {
        DialogUIUtils.showToastLong(msg.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_custom_alert:
//                View rootView = View.inflate(activity, R.layout.custom_dialog_layout, null);
//                DialogUIUtils.showCustomAlert(this, rootView).show();
                break;
            case R.id.btn_toast:
//                DialogUIUtils.showToastTie(this, msg).show();
                break;
            case R.id.btn_loading_vertical:
//                DialogUIUtils.showLoadingVertical(this, "加载中...").show();
                break;
            case R.id.btn_loading_horizontal:
//                DialogUIUtils.showLoadingHorizontal(this, "加载中...").show();
                break;
            case R.id.btn_md_loading_vertical:
//                DialogUIUtils.showMdLoadingVertical(this, "加载中...").show();
                break;
            case R.id.btn_md_loading_horizontal:
//                DialogUIUtils.showMdLoadingHorizontal(this, "加载中...").show();
                break;
            case R.id.btn_md_alert:
                DialogUIUtils.showMdAlert(this, "标题", msg, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_tie_alert:
                DialogUIUtils.showAlert(this, "标题", msg, true, false, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_alert_horizontal:
//                DialogUIUtils.showAlertHorizontal(activity, "标题", msg, new DialogUIListener() {
//                    @Override
//                    public void onPositive() {
//                        showToast("onPositive");
//                    }
//
//                    @Override
//                    public void onNegative() {
//                        showToast("onNegative");
//                    }
//
//                }).show();
                break;
            case R.id.btn_alert_vertical:
//                DialogUIUtils.showAlertVertical(this, "标题", msg, new DialogUIListener() {
//                    @Override
//                    public void onPositive() {
//                        showToast("onPositive");
//                    }
//
//                    @Override
//                    public void onNegative() {
//                        showToast("onNegative");
//                    }
//
//                }).show();
                break;
            case R.id.btn_bottom_sheet_cancel: {
                List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add("3");
                DialogUIUtils.showBottomSheetAndCancel(context, strings, "取消", new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                }).show();
            }
            break;
            case R.id.btn_center_sheet:
                List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add("3");
//                DialogUIUtils.showCenterSheet(activity, strings, new DialogUIItemListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
//                        showToast(text);
//                    }
//
//                    @Override
//                    public void onBottomBtnClick() {
//                        showToast("onItemClick");
//                    }
//                }).show();

                break;
            case R.id.btn_alert_input:
//                DialogUIUtils.showAlertInput(activity, "登录", "请输入用户名", "请输入密码", "登录", "取消", new DialogUIListener() {
//                    @Override
//                    public void onPositive() {
//
//                    }
//
//                    @Override
//                    public void onNegative() {
//
//                    }
//
//                    @Override
//                    public void onGetInput(CharSequence input1, CharSequence input2) {
//                        super.onGetInput(input1, input2);
//                        showToast("input1:" + input1 + "--input2:" + input2);
//                    }
//                }).show();
                break;
            case R.id.btn_alert_multichoose:
                String[] words = new String[]{"1", "2", "3"};
                boolean[] choseDefault = new boolean[]{false, false, false};
//                DialogUIUtils.showMdMultiChoose(activity, "标题", words, choseDefault, new DialogUIListener() {
//                    @Override
//                    public void onPositive() {
//
//                    }
//
//                    @Override
//                    public void onNegative() {
//
//                    }
//                }).show();
                break;
            case R.id.btn_alert_singlechoose:
                String[] words2 = new String[]{"1", "2", "3"};
//                DialogUIUtils.showSingleChoose(activity, "单选", 0, words2, new DialogUIItemListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
//                        showToast(text + "--" + position);
//                    }
//                }).show();
                break;
            case R.id.btn_bottom_sheet:
//                List<BottomSheetBean> datass = new ArrayList<>();
//                datass.add(new BottomSheetBean(0, "1"));
//                datass.add(new BottomSheetBean(0, "2"));
//                datass.add(new BottomSheetBean(0, "3"));
//                DialogUIUtils.showBottomSheet(this, datass, new DialogUIItemListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
//
//                    }
//                }).show();
                break;
            case R.id.btn_md_bottom_vertical:
//                List<BottomSheetBean> datas2 = new ArrayList<>();
//                datas2.add(new BottomSheetBean(0, "1"));
//                datas2.add(new BottomSheetBean(0, "2"));
//                datas2.add(new BottomSheetBean(0, "3"));
//                datas2.add(new BottomSheetBean(0, "4"));
//                datas2.add(new BottomSheetBean(0, "5"));
//                datas2.add(new BottomSheetBean(0, "6"));
//                DialogUIUtils.showMdBottomSheetVertical(activity, "标题", datas2, "this is cancle button", new DialogUIItemListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
//                        showToast(text + "---" + position);
//                    }
//                }).show();
                break;
            case R.id.btn_md_bottom_horizontal:
//                List<BottomSheetBean> datas3 = new ArrayList<>();
//                datas3.add(new BottomSheetBean(0, "1"));
//                datas3.add(new BottomSheetBean(0, "2"));
//                datas3.add(new BottomSheetBean(0, "3"));
//                datas3.add(new BottomSheetBean(0, "4"));
//                datas3.add(new BottomSheetBean(0, "5"));
//                datas3.add(new BottomSheetBean(0, "6"));
//                DialogUIUtils.showMdBottomSheetHorizontal(activity, "标题", datas3, "this is cancle button", 3, new DialogUIItemListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
//                        showToast(text + "---" + position);
//                    }
//                }).show();
                break;
        }
    }
}
