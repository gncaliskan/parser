package com.yoncaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yoncaapp.Adapter.ReservedRecyclerAdapter;
import com.yoncaapp.Adapter.WinnerRecyclerAdapter;
import com.yoncaapp.Model.Comment;
import com.yoncaapp.Service.ScreenRecordingService;
import com.yoncaapp.System.Constants;

import java.util.List;

public class ResultActivity extends BaseActivity {

    List<Comment> royal, reserve;
    RecyclerView royalRecycler, reservedRecycler;
    Button repeat, stopRecording, shareVideo;
    TextView reservedLabel;
    boolean unique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        royal = (List<Comment>) getIntent().getSerializableExtra(Constants.ROYAL_LIST);
        reserve = (List<Comment>) getIntent().getSerializableExtra(Constants.RESERVE_LIST);
        unique = getIntent().getBooleanExtra(Constants.UNIQUE_USER, false);
        reservedLabel = findViewById(R.id.result_yedek_label);
        if(reserve.size()==0){
            reservedLabel.setVisibility(View.GONE);
        }

        royalRecycler = findViewById(R.id.result_royal_winners);
        reservedRecycler = findViewById(R.id.result_reserved_winners);

        WinnerRecyclerAdapter royalAdapter = new WinnerRecyclerAdapter(royal, ResultActivity.this, unique);
        if (royal.size() > 1) {
            royalRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            royalRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }
        royalRecycler.setAdapter(royalAdapter);


        ReservedRecyclerAdapter reservedAdapter = new ReservedRecyclerAdapter(reserve, ResultActivity.this, unique);
        reservedRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        reservedRecycler.setAdapter(reservedAdapter);

        repeat = findViewById(R.id.result_repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
                startActivity(intent);
            }
        });
        stopRecording = findViewById(R.id.result_stop_recording);
        shareVideo = findViewById(R.id.result_share_video);

        if(ScreenRecordingService.getStatus()!=null && ScreenRecordingService.getStatus().equals(Constants.RECORDING)) {
            shareVideo.setVisibility(View.GONE);
            stopRecording.setVisibility(View.VISIBLE);
            stopRecording.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScreenRecordingService.stopRecording();
                    ScreenRecordingService.setStatus(Constants.STOPPED);
                    stopRecording.setVisibility(View.GONE);
                    finish();
                    startActivity(getIntent());
                }
            });
        }else if(ScreenRecordingService.getStatus()!=null && ScreenRecordingService.getStatus().equals(Constants.STOPPED)) {
            shareVideo.setVisibility(View.VISIBLE);
            stopRecording.setVisibility(View.GONE);
            shareVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScreenRecordingService.createVideoShareIntent();
                }
            });
        }else{
            stopRecording.setVisibility(View.GONE);
            shareVideo.setVisibility(View.GONE);
        }



    }




}
