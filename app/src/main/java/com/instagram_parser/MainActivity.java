package com.instagram_parser;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.instagram_parser.Service.FetchCommentsService;


public class MainActivity extends Activity {

    private static Context mainContext;
    private Button loadComments;
    private EditText postUrl;
    private static String URL = "https://www.instagram.com/p/B0EKAEBFye0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = MainActivity.this;

        loadComments = findViewById(R.id.main_getComments);
        postUrl = findViewById(R.id.main_postUrl);


        loadComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                new FetchCommentsService(postUrl.getText().toString()).execute();
            }
            }
        });

    }

    public static Context getMainContext() {
        return mainContext;
    }
}