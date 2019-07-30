package com.instagram_parser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.instagram_parser.Adapter.ReservedListAdapter;
import com.instagram_parser.Adapter.WinnerRecyclerAdapter;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.Service.ScreenRecordingService;
import com.instagram_parser.System.Constants;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    List<Comment> royal, reserve;
    RecyclerView royalRecycler;
    ListView reservedListView;
    Button repeat, stopRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        royal = (List<Comment>) getIntent().getSerializableExtra(Constants.ROYAL_LIST);
        reserve = (List<Comment>) getIntent().getSerializableExtra(Constants.RESERVE_LIST);

        royalRecycler = findViewById(R.id.result_royal_winners);
        reservedListView = findViewById(R.id.result_reserved_winners);

        WinnerRecyclerAdapter royalAdapter = new WinnerRecyclerAdapter(royal);
        if (royal.size() > 1) {
            royalRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            royalRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }
        royalRecycler.setAdapter(royalAdapter);

        ReservedListAdapter reservedAdapter = new ReservedListAdapter(getApplicationContext(), reserve);
        reservedListView.setAdapter(reservedAdapter);

        repeat = findViewById(R.id.result_repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
                startActivity(intent);
            }
        });
        stopRecording = findViewById(R.id.result_stop_recording);
        stopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenRecordingService.stopRecording();

            }
        });



    }




}
