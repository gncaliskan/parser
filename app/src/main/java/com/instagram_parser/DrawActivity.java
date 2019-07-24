package com.instagram_parser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.instagram_parser.Adapter.CommentListAdapter;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.System.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class DrawActivity extends AppCompatActivity {

    List<Comment> comments;
    CommentListAdapter commentListAdapter;
    ListView commentListView;
    EditText asilCount, yedekCount;
    Button cekilisYap;
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        commentListView = findViewById(R.id.draw_comment_list);
        asilCount = findViewById(R.id.draw_asil_text);
        yedekCount = findViewById(R.id.draw_yedek_text);
        cekilisYap = findViewById(R.id.draw_cekilis_button);

        comments = (List<Comment>) getIntent().getSerializableExtra(Constants.COMMENT_LIST);
        commentListAdapter= new CommentListAdapter(getApplicationContext(), comments);
        commentListView.setAdapter(commentListAdapter);


        cekilisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Comment> selected = getSelectedComments();
                int listSize = selected.size();
                Set<Comment> winners = new HashSet<>();
                if(inputControl(listSize)) {
                    do {
                        Random rand = new Random();
                        int n = rand.nextInt(listSize);
                        winners.add(selected.get(n));
                    } while (winners.size() < total);
                }

                StringBuilder sb = new StringBuilder();
                for (Comment a : winners) {
                    sb.append(a.getOwnerName()).append(" - ");
                }
                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Comment> getSelectedComments(){
        List<Comment> selectedComments = new ArrayList<>();
        for (Comment c:comments) {
            if(c.isActive()){
                selectedComments.add(c);
            }

        }
        return selectedComments;
    }

    private boolean inputControl(int selectedSize){
        int asil = 0;
        if(!asilCount.getText().toString().isEmpty()){
            asil =Integer.valueOf(asilCount.getText().toString());
        }
        int yedek = 0;
        if(!yedekCount.getText().toString().isEmpty()){
            yedek =Integer.valueOf(yedekCount.getText().toString());
        }
        if(asil == 0){
            Toast.makeText(getApplicationContext(), "Asıl talihli sayısını girmelisiniz.", Toast.LENGTH_LONG).show();
            return false;
        }
        total = asil + yedek;
        if(total> selectedSize){
            Toast.makeText(getApplicationContext(), "Talihli sayınız yorumların sayısından büyük olamaz.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
