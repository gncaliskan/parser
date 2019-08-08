package com.yoncaapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yoncaapp.Adapter.CommentListAdapter;
import com.yoncaapp.Model.Comment;
import com.yoncaapp.Model.CommentList;
import com.yoncaapp.System.Constants;
import com.yoncaapp.Util.LogicUtil;
import com.yoncaapp.Util.ToastUtil;

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

public class DrawActivity extends BaseActivity {

    private List<Comment> comments;
    private CommentListAdapter commentListAdapter;
    private EditText asilCount;
    private EditText yedekCount;
    private EditText etiketCount;
    private EditText kelime;
    private CheckBox multipleComment;
    private Map<String, List<Comment>> userCommentMap;
    private int total;
    private int asil;
    private int uniqueListSize = 0;
    private Dialog rulesDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ListView commentListView = (ListView) findViewById(R.id.draw_comment_list);
        asilCount = (EditText) findViewById(R.id.draw_asil_text);
        yedekCount = (EditText) findViewById(R.id.draw_yedek_text);
        final Button cekilisYap = (Button) findViewById(R.id.draw_cekilis_button);
        Button rulesButton = (Button) findViewById(R.id.draw_rules_button);
        ConstraintLayout drawLayout = (ConstraintLayout) findViewById(R.id.draw_layout);
        TextView totalSize = (TextView) findViewById(R.id.draw_total_comment_size);
        createRulesDialog();
        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rulesDialog.show();
            }
        });
        setupUI(drawLayout, this);

        List<Comment> selectedComments = new ArrayList<>();
        comments = CommentList.getInstance().getComments();
        convertListToMap();
        if(comments!=null) {
            totalSize.setText(comments.size() + " " + getResources().getString(R.string.yorumCekildi));
        }


        commentListAdapter = new CommentListAdapter(getApplicationContext(),CommentList.getInstance().getComments(), true);
        commentListView.setAdapter(commentListAdapter);

        cekilisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekilisiYap();
            }
        });



    }

    private void cekilisiYap(){
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
            intent.putExtra(Constants.UNIQUE_USER, multipleComment.isChecked());
            startActivity(intent);
        }
    }

    private void createRulesDialog(){
        rulesDialog = new Dialog(this);
        rulesDialog.setContentView(R.layout.dialog_rules);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.60);

        rulesDialog.getWindow().setLayout(width, height);
        multipleComment = rulesDialog.findViewById(R.id.draw_multiple_comment);
        etiketCount = rulesDialog.findViewById(R.id.draw_etiket_text);
        kelime = rulesDialog.findViewById(R.id.draw_kelime_text);
        Button submitRules = rulesDialog.findViewById(R.id.draw_rules_submit);
        ImageView closeRules = rulesDialog.findViewById(R.id.draw_rules_close);
        submitRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterComments();
                rulesDialog.dismiss();
            }
        });
        closeRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rulesDialog.dismiss();
            }
        });

    }


    private List<Comment> getSelectedComments() {
        filterComments();
        CommentList.getInstance().setCommentMap(userCommentMap);
        List<Comment> selectedComments = new ArrayList<>();
        uniqueListSize = 0;
        for (Comment c : CommentList.getInstance().getComments()) {
            if (LogicUtil.getActiveStatus(c)) {
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
        int yedek = 0;
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
        String matchedWord="";
        if (!etiketCount.getText().toString().isEmpty()) {
            labelCount = Integer.valueOf(etiketCount.getText().toString());
        }

        if(!kelime.getText().toString().isEmpty()){
            matchedWord = kelime.getText().toString().trim();
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
                        c.setContainLabelCount(false);
                    }
                }
            } else {
                for (Comment c : cList) {
                    c.setContainLabelCount(true);
                }
            }

            if (!matchedWord.isEmpty()) {
                for (Comment c : cList) {
                    if(c.getCommentText().contains(matchedWord) ||
                            c.getCommentText().contains(matchedWord.toLowerCase()) ||
                            c.getCommentText().contains(matchedWord.toUpperCase())){
                        c.setMatched(true);
                    }else{
                        c.setMatched(false);
                    }

                }
            } else {
                for (Comment c : cList) {
                    c.setMatched(true);
                }
            }
            if (multipleRejected) {
                if (cList.size() > 1) {
                    int acceptedCount = 0;
                    for (Comment c : cList) {
                        if (c.isNotDeleted() && c.isContainLabelCount() && c.isMatched()) {
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
