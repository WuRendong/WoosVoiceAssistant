package com.flyingost.woosvoiceassistant;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.flyingost.woosvoiceassistant.ui.FloatingUIService;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String permissions[] = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 4567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this, permissions, 101);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE:
                Log.i(TAG, "resultCode: " + resultCode + " data: " + data);
                if (resultCode == 0) {
                    ActivityCompat.requestPermissions(this, permissions, 101);
                }
//                finish();
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
        switch (requestCode) {
            case 101:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int i = -1;
                    ArrayList<String> permissionDisplayList = new ArrayList<String>();
                    for (String per : permissions) {
                        i++;
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            if (!shouldShowRequestPermissionRationale(per)) {
                                String permissionName = getPermissionString(this, per);
                                permissionDisplayList.add(permissionName);
                            }
                        }
                    }

//                    if (permissionDisplayList.contains(this.getResources().getString(R.string.permission_alert_window))) {
//                        Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                        startActivity(myIntent);
//                        permissionDisplayList.remove(this.getResources().getString(R.string.permission_alert_window));
//                    }

                    if (!permissionDisplayList.isEmpty()) {
                        String permissionStr = permissionDisplayList.get(0);
                        if (permissionDisplayList.size() == permissions.length) {
                            permissionStr = this.getResources().getString(R.string.permission_all);
                        } else if (permissionDisplayList.size() > 1
                                && permissionDisplayList.size() < permissions.length) {
                            for (int j = 1; j < permissionDisplayList.size(); j++) {
                                permissionStr = permissionStr + "、" + permissionDisplayList.get(j);
                            }
                        }
                        showPermissionDialog(permissionStr);
                    } else {
//                        CommonUtil.startVoiceSpeech();
                        Intent intent = new Intent();
                        intent.setClass(this, FloatingUIService.class);
                        startService(intent);
                        finish();
                    }
                }
                break;
        }


    }

    public String getPermissionString(Context context, String permission) {
        String permName = null;
        if (TextUtils.equals(permission, Manifest.permission.ACCESS_FINE_LOCATION)) {
            permName = context.getResources().getString(R.string.permission_location);
        } else if (TextUtils.equals(permission, Manifest.permission.CALL_PHONE)) {
            permName = context.getResources().getString(R.string.permission_phone);
        } else if (TextUtils.equals(permission, Manifest.permission.READ_CONTACTS)) {
            permName = context.getResources().getString(R.string.permission_contact);
        } else if (TextUtils.equals(permission, Manifest.permission.SEND_SMS)) {
            permName = context.getResources().getString(R.string.permission_sms);
        } else if (TextUtils.equals(permission, Manifest.permission.RECORD_AUDIO)) {
            permName = context.getResources().getString(R.string.permission_voice_recorder);
        }else if (TextUtils.equals(permission, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            permName = context.getResources().getString(R.string.permission_read_external_storage);
        }else if(TextUtils.equals(permission, Manifest.permission.SYSTEM_ALERT_WINDOW)){
            permName = context.getResources().getString(R.string.permission_alert_window);
        }
        return permName;
    }

    private AlertDialog mAlertDialog;
    private void showPermissionDialog(final String permissions) {
        if (mAlertDialog == null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle(R.string.permission_dialog_title);

            String permissionMessage = this.getResources().getString(
                    R.string.permission_dialog_message);
            dialogBuilder.setMessage(String.format(permissionMessage, permissions));

            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton(R.string.permission_dialog_positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAlertDialog = null;
                    Intent localIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
                    localIntent.setData(uri);
                    MainActivity.this.startActivity(localIntent);
                }
            });

            dialogBuilder.setNegativeButton(R.string.permission_dialog_negative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAlertDialog = null;
                    finish();
                }
            });

            mAlertDialog = dialogBuilder.create();
            mAlertDialog.show();
        }
    }
}
