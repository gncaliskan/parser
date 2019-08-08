package com.yoncaapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yoncaapp.Service.FetchCommentsService;
import com.yoncaapp.Service.ScreenRecordingService;
import com.yoncaapp.System.Constants;
import com.yoncaapp.Util.ToastUtil;

import java.util.ArrayList;



public class MainActivity extends BaseActivity {

    private static Context mainContext;
    private EditText postUrl;
    private CheckBox record;
    private static String URL = "https://www.instagram.com/p/B0EKAEBFye0";
    private boolean recordPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = MainActivity.this;
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        setupUI(mainLayout, this);
        Button loadComments = (Button) findViewById(R.id.main_getComments);
        loadComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    if (!record.isChecked()) {
                        recordPermission = false;
                        ScreenRecordingService.setStatus(Constants.WILL_NOT_RECORD);
                    }else{
                        ScreenRecordingService.setStatus(Constants.WILL_RECORD);
                    }

                    if(!postUrl.getText().toString().isEmpty() && postUrl.getText().toString().contains(getResources().getString(R.string.urlInstagram))) {
                        new FetchCommentsService(postUrl.getText().toString(), MainActivity.this).execute();
                    }else{
                        ToastUtil.show(MainActivity.this,R.string.urlGirilmedi, ToastUtil.TOAST_ERROR);
                    }
                }
            }
        });
        postUrl = (EditText) findViewById(R.id.main_postUrl);
        record = (CheckBox) findViewById(R.id.main_record);
        record.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (record.isChecked()) {
                    if (!recordPermission) {
                        if (ScreenRecordingService.mMediaProjection == null) {
                            // This asks for user permissions to capture the screen
                            ScreenRecordingService.mProjectionManager = (MediaProjectionManager) getSystemService
                                    (MEDIA_PROJECTION_SERVICE);
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                            startActivityForResult(ScreenRecordingService.mProjectionManager.createScreenCaptureIntent(), ScreenRecordingService.CAST_PERMISSION_CODE);
                            return;
                        }
                    }
                }
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != ScreenRecordingService.CAST_PERMISSION_CODE) {
            // Where did we get this request from ? -_-
            return;
        }
        if (resultCode != RESULT_OK) {
            ToastUtil.show(this,R.string.ekranKayitIzniVerilmedi, ToastUtil.TOAST_ERROR);
            record.setChecked(false);
            return;
        }

        ScreenRecordingService.mMediaProjection = ScreenRecordingService.mProjectionManager.getMediaProjection(resultCode, data);
        ScreenRecordingService.mMediaRecorder = new MediaRecorder();
        ScreenRecordingService.mDisplayMetrics = new DisplayMetrics();
        ScreenRecordingService.resources = new ArrayList<>();
        getWindowManager().getDefaultDisplay().getMetrics(ScreenRecordingService.mDisplayMetrics);

        recordPermission = true;
    }

    public static Context getMainContext() {
        return mainContext;
    }


}