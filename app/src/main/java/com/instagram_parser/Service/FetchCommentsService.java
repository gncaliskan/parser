package com.instagram_parser.Service;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.instagram_parser.MainActivity;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.Model.Edges;
import com.instagram_parser.Model.Node;
import com.instagram_parser.Model.Shortcode_media;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class FetchCommentsService extends AsyncTask<Void, Void, Void> {
    private ProgressDialog progressDialog;

    private String url;

    public FetchCommentsService(String url) {
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(MainActivity.getMainContext());
        progressDialog.setTitle("YORUMLAR");
        progressDialog.setMessage("Yorumlar Çekiliyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            Document doc = Jsoup.connect(url).get();

            StringBuilder htmlString = new StringBuilder();
            htmlString.append(doc.outerHtml());
            String subHtml = htmlString.substring(htmlString.indexOf("window._sharedData = ") + 21);
            String sharedData = subHtml.substring(0, subHtml.indexOf(";</script> "));
            JsonParser parser = new JsonParser();

            JsonElement jsonTree = parser.parse(sharedData);
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                JsonElement entry_data = jsonObject.get("entry_data");

                if (entry_data.isJsonObject()) {
                    JsonObject entry_dataObject = entry_data.getAsJsonObject();
                    JsonElement postPage = entry_dataObject.get("PostPage");

                    if (postPage.isJsonArray()) {
                        JsonArray postPageArray = postPage.getAsJsonArray();
                        JsonObject postPageObject = (JsonObject) postPageArray.get(0);
                        JsonElement graphql = postPageObject.get("graphql");

                        if (graphql.isJsonObject()) {
                            JsonObject graphqlObject = graphql.getAsJsonObject();

                            JsonElement shortcode_media = graphqlObject.get("shortcode_media");
                            Gson gson = new Gson();
                            Shortcode_media shortcodeMedia = gson.fromJson(shortcode_media, Shortcode_media.class);
                            convertToCommentList(shortcodeMedia);
                        }
                    }
                }

            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }

    private List<Comment> convertToCommentList(Shortcode_media shortcode_media) {
        List<Comment> comments = new ArrayList<>();
        List<Edges> mediaEdgeList = shortcode_media.getEdge_media_to_parent_comment().getEdges();
        for (Edges edge : mediaEdgeList) {
            Node node = edge.getNode();
            Comment comment = new Comment(node.getId(), node.getOwner().getUsername(), node.getText(), node.getOwner().getProfile_pic_url(), true);
            comments.add(comment);
        }
        return comments;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

//            yazarlar_layout = (LinearLayout) findViewById(R.id.yazarlar_layout);
//            TextView txt_yazarlar = (TextView) findViewById(R.id.txt_yazarlar);
//            yazarlar_layout.setVisibility(View.VISIBLE);
//            txt_yazarlar.setText(authors);
        progressDialog.dismiss();
    }
}
