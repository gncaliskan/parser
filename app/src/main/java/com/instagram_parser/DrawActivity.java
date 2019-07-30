package com.instagram_parser;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.instagram_parser.Adapter.CommentListAdapter;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.Model.CommentList;
import com.instagram_parser.System.Constants;
import com.instagram_parser.Util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawActivity extends AppCompatActivity {

    List<Comment> comments;
    CommentListAdapter commentListAdapter;
    ListView commentListView;
    EditText asilCount, yedekCount, etiketCount;
    CheckBox multipleComment;
    Button cekilisYap,rulesButton;
    Map<String, List<Comment>> userCommentMap;
    int total, asil, yedek, uniqueListSize = 0;
    List<Comment> selectedComments;
    TextView totalSize;
    ConstraintLayout rulesLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        commentListView = findViewById(R.id.draw_comment_list);
        asilCount = findViewById(R.id.draw_asil_text);
        yedekCount = findViewById(R.id.draw_yedek_text);
        etiketCount = findViewById(R.id.draw_etiket_text);
        cekilisYap = findViewById(R.id.draw_cekilis_button);
        multipleComment = findViewById(R.id.draw_multiple_comment);
        rulesButton = findViewById(R.id.draw_rules_button);
        rulesLayout = findViewById(R.id.draw_rules);
        totalSize = findViewById(R.id.draw_total_comment_size);
        rulesLayout.setVisibility(View.GONE);
        selectedComments = new ArrayList<>();
        comments = CommentList.getInstance().getComments();
        convertListToMap();
        if(comments!=null) {
            totalSize.setText(String.valueOf(comments.size()) + " " + getResources().getString(R.string.yorumCekildi));
        }


        commentListAdapter = new CommentListAdapter(getApplicationContext());
        commentListView.setAdapter(commentListAdapter);

        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               toggleRules();
            }
        });

        cekilisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Comment> selected = getSelectedComments();
                Set<Comment> winners = new HashSet<>();
                if (inputControl(uniqueListSize)) {
                    do {
                        Random rand = new Random();
                        int n = rand.nextInt(selected.size());
                        boolean alreadySelected = false;
                        for (Comment winnerComment : winners) {
                            if (winnerComment.getOwnerId().equals(selected.get(n).getOwnerId())) {
                                alreadySelected = true;
                                break;
                            }
                        }
                        if (!alreadySelected) {
                            winners.add(selected.get(n));
                        }
                    } while (winners.size() < total);


                    List<Comment> winnerList = new ArrayList<>(winners);
                    List<Comment> royal = new ArrayList<>(winnerList.subList(0, asil));
                    List<Comment> reserve = new ArrayList<>(winnerList.subList(asil, total));

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra(Constants.RESERVE_LIST, (Serializable) reserve);
                    intent.putExtra(Constants.ROYAL_LIST, (Serializable) royal);
                    startActivity(intent);
                }
            }
        });

        multipleComment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filterComments();
            }
        });

        etiketCount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                filterComments();
            }
        });
    }

    private void toggleRules(){
        if(rulesLayout.getVisibility() == View.GONE){
            rulesLayout.setVisibility(View.VISIBLE);
        }else{
            rulesLayout.setVisibility(View.GONE);
        }
    }


    private List<Comment> getSelectedComments() {
        filterComments();
        CommentList.getInstance().setCommentMap(userCommentMap);
        List<Comment> selectedComments = new ArrayList<>();
        uniqueListSize = 0;
        for (Comment c : CommentList.getInstance().getComments()) {
            if (c.isNotDeleted()) {
                boolean alreadySelectedUser = false;
                for (Comment selected : selectedComments) {
                    if (selected.getOwnerId().equals(c.getOwnerId())) {
                        alreadySelectedUser = true;
                        break;
                    }
                }
                if (!alreadySelectedUser) {
                    uniqueListSize++;
                }
                selectedComments.add(c);
            }
        }
        return selectedComments;
    }

    private void convertListToMap() {
        userCommentMap = new HashMap<>();
        if (comments != null && comments.size() > 0) {
            for (Comment c : comments) {
                if (userCommentMap.containsKey(c.getOwnerId())) {
                    userCommentMap.get(c.getOwnerId()).add(c);
                } else {
                    List<Comment> cList = new ArrayList<>();
                    cList.add(c);
                    userCommentMap.put(c.getOwnerId(), cList);
                }
            }
        }
    }

    private void convertMapToList() {
        ArrayList<List<Comment>> commentArrayList;
        List<Comment> commentList;
        commentArrayList = new ArrayList<>(userCommentMap.values());
        commentList = new ArrayList<>();
        for (List<Comment> commentArray : commentArrayList) {
            commentList.addAll(commentArray);
        }
        CommentList.getInstance().setComments(commentList);
    }

    private boolean inputControl(int selectedSize) {
        asil = 0;
        if (!asilCount.getText().toString().isEmpty()) {
            asil = Integer.valueOf(asilCount.getText().toString());
        }
        yedek = 0;
        if (!yedekCount.getText().toString().isEmpty()) {
            yedek = Integer.valueOf(yedekCount.getText().toString());
        }
        if (asil == 0) {
            ToastUtil.show(DrawActivity.this, R.string.asilTalihliSayisiError, ToastUtil.TOAST_ERROR);
            return false;
        }
        total = asil + yedek;
        if (total > selectedSize) {
            ToastUtil.show(DrawActivity.this, R.string.talihliYorumdanBuyuk, ToastUtil.TOAST_WARNING);
            return false;
        }
        if (total > uniqueListSize) {
            ToastUtil.show(DrawActivity.this, R.string.talihliKatilandanBuyuk, ToastUtil.TOAST_WARNING);
            return false;
        }

        return true;
    }

    private void filterComments() {
        int labelCount = 0;
        if (!etiketCount.getText().toString().isEmpty()) {
            labelCount = Integer.valueOf(etiketCount.getText().toString());
        }
        boolean multipleRejected = multipleComment.isChecked();

        for (Map.Entry<String, List<Comment>> entry : userCommentMap.entrySet()) {
            List<Comment> cList = entry.getValue();
            if (labelCount > 0) {
                for (Comment c : cList) {
                    Pattern word = Pattern.compile("@[A-Za-z0-9_./#&+-]");
                    Matcher m = word.matcher(c.getCommentText());
                    int labelSize = 0;
                    while (m.find()) {
                        labelSize++;
                    }
                    if (labelSize < labelCount) {
                        c.setMathedLabelCount(false);
                    }
                }
            } else {
                for (Comment c : cList) {
                    c.setMathedLabelCount(true);
                }
            }
            if (multipleRejected) {
                if (cList.size() > 1) {
                    int acceptedCount = 0;
                    for (Comment c : cList) {
                        if (c.isNotDeleted() && c.isMathedLabelCount()) {
                            acceptedCount++;
                        }
                        if (acceptedCount > 1) {
                            c.setAccepted(false);
                        }
                    }
                }
            } else {
                for (Comment c : cList) {
                    c.setAccepted(true);
                }
            }

        }
        convertMapToList();
        commentListAdapter.notifyDataSetChanged();

    }


}
