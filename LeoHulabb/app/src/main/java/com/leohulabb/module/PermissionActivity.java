package com.leohulabb.module;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.commonui.activity.base.BaseActivity;
import com.commonui.dialog.DialogUIListener;
import com.commonui.dialog.DialogUIUtils;
import com.commonui.toast.ToastManager;
import com.leohulabb.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends BaseActivity
{
    private int REQUEST_CODE_PERMISSION = 0x00099;

    @Override
    public int getLayoutId() {
        return R.layout.test_permission;
    }

    @Override
    public void initPresenter() { }

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
     * 请求权限
     * @param permission     请求的权限
     * @param requestCode    请求权限的请求码
     */
    public void requestPermission(String[] permission, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;

        if (checkPermissions(permission)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permission);
            ActivityCompat.requestPermissions(this,
                    needPermissions.toArray(new String[needPermissions.size()]),
                    REQUEST_CODE_PERMISSION);
        }
    }

    private boolean checkPermissions(String[] permissions)
    {
        //6.0以下不需检验权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            //未获取权限
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showPermissionDialog();
            }
        }
    }

    private void showPermissionDialog() {

        ToastManager.show("当前应用缺少必要权限，该功能暂时无法使用");
        DialogUIUtils.showMdAlert(this, "请求权限", "当前应用缺少必要权限，该功能暂时无法使用",
                new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        startAppSettings();
                    }

                    @Override
                    public void onNegative() {

                    }
                }).show();
    }

    private void startAppSettings()
    {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 确认所有的权限是否都授权
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int granResult : grantResults) {
            if (granResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限获取成功
     * @param requestCode 权限
     */
    public void permissionSuccess(int requestCode) {
        Log.e(getClass().getName(), "获取权限成功=" + requestCode);
    }

    public void permissionFail(int requestCode) {
        Log.e(getClass().getName(), "获取权限失败=" + requestCode);
    }
}
