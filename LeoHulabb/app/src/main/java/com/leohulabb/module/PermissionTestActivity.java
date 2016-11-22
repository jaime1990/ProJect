package com.leohulabb.module;

import android.Manifest;
import android.view.View;

import com.commonui.activity.base.BaseActivity;
import com.commonui.dialog.DialogUIListener;
import com.commonui.dialog.DialogUIUtils;
import com.commonui.toast.ToastManager;
import com.commonutils.MPermissionUtils;
import com.leohulabb.R;

public class PermissionTestActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.test_permission;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setListener() {

    }

    /**
     * 打电话
     *
     * @param view
     */
    public void onClick1(View view) {
        MPermissionUtils.requestPermissionsResult(this, 1, new String[]{Manifest.permission.CALL_PHONE}
                , new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ToastManager.show("授权成功,执行拨打电话操作!");
                    }

                    @Override
                    public void onPermissionDenied() {
                        DialogUIUtils.showMdAlert((PermissionTestActivity) context, "请求权限", "当前应用缺少必要权限，该功能暂时无法使用",
                                new DialogUIListener() {
                                    @Override
                                    public void onPositive() {
                                        MPermissionUtils.startAppSettings(context);
                                    }

                                    @Override
                                    public void onNegative() {

                                    }
                                }).show();
                    }
                });
    }

    /**
     * 写SD卡
     *
     * @param view
     */
    public void onClick2(View view) {
        MPermissionUtils.requestPermissionsResult(this, 1, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                , new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ToastManager.show("授权成功,执行拨打电话操作!");
                    }

                    @Override
                    public void onPermissionDenied() {
                        DialogUIUtils.showMdAlert((PermissionTestActivity) context, "请求权限", "当前应用缺少必要权限，该功能暂时无法使用",
                                new DialogUIListener() {
                                    @Override
                                    public void onPositive() {
                                        MPermissionUtils.startAppSettings(context);
                                    }

                                    @Override
                                    public void onNegative() {

                                    }
                                }).show();
                    }
                });
    }

    /**
     * 拍照
     *
     * @param view
     */
    public void onClick3(View view) {
        MPermissionUtils.requestPermissionsResult(this, 1, new String[]{Manifest.permission.CAMERA}
                , new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ToastManager.show("授权成功,执行拨打电话操作!");
                    }

                    @Override
                    public void onPermissionDenied() {
                        DialogUIUtils.showMdAlert((PermissionTestActivity) context, "请求权限", "当前应用缺少必要权限，该功能暂时无法使用",
                                new DialogUIListener() {
                                    @Override
                                    public void onPositive() {
                                        MPermissionUtils.startAppSettings(context);
                                    }

                                    @Override
                                    public void onNegative() {

                                    }
                                }).show();
                    }
                });
    }

    /**
     * 权限成功回调函数
     *
     * @param requestCode
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}