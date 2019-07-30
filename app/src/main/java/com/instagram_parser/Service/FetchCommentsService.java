package com.instagram_parser.Service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.instagram_parser.DrawActivity;
import com.instagram_parser.MainActivity;
import com.instagram_parser.Model.Comment;
import com.instagram_parser.Model.CommentList;
import com.instagram_parser.Model.Edges;
import com.instagram_parser.Model.Node;
import com.instagram_parser.Model.Shortcode_media;
import com.instagram_parser.System.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FetchCommentsService extends AsyncTask<Void, Void, Void> {
    public static final String REQUEST_METHOD_GET = "GET";
    public static final int READ_TIMEOUT = 150000;
    public static final int CONNECTION_TIMEOUT = 150000;
    public static final int FIRST = 50;
    public static final int FIRST_COMMENTS_SIZE = 24;

    private ProgressDialog progressDialog;
    private String url;
    private List<Comment> comments;
    private int commentSize;
    List<Edges> mediaEdgeList;
    JsonParser parser;
    Gson gson;
    boolean record;

    public FetchCommentsService(String url, boolean record) {
        this.record = record;
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
            mediaEdgeList = new ArrayList<>();
            parser = new JsonParser();
            gson = new Gson();
            StringBuilder htmlString = new StringBuilder();
            htmlString.append(doc.outerHtml());
            String subHtml = htmlString.substring(htmlString.indexOf("window._sharedData = ") + 21);
            String sharedData = subHtml.substring(0, subHtml.indexOf(";</script> "));

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
                            Shortcode_media shortcodeMedia = gson.fromJson(shortcode_media, Shortcode_media.class);
                            commentSize = shortcodeMedia.getEdge_media_to_parent_comment().getCount();
                            comments = new ArrayList<>();
                            convertToCommentList(shortcodeMedia);
                            //if (shortcodeMedia.getEdge_media_to_parent_comment().getEdges().size() == FIRST_COMMENTS_SIZE) {
                            getNextComments(shortcodeMedia, shortcodeMedia.getEdge_media_to_parent_comment().getPage_info().getEnd_cursor());
                            //}
                        }
                    }
                }

            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }

    private void convertToCommentList(Shortcode_media shortcode_media) {
        mediaEdgeList.clear();
        if (shortcode_media.getEdge_media_to_parent_comment() != null) {
            mediaEdgeList.addAll(shortcode_media.getEdge_media_to_parent_comment().getEdges());
        }
        if (shortcode_media.getEdge_media_to_comment() != null) {
            mediaEdgeList.addAll(shortcode_media.getEdge_media_to_comment().getEdges());
        }
        for (Edges edge : mediaEdgeList) {
            Node node = edge.getNode();
            Comment comment = new Comment(node.getId(), node.getOwner().getUsername(), node.getOwner().getId(), node.getText(), node.getOwner().getProfile_pic_url(), node.getCreated_at());
            if (comments.size() < commentSize) {
                comments.add(comment);
            } else {
                break;
            }
        }
    }

    private void getNextComments(Shortcode_media shortcode_media, String after) {
        if (after != null) {
            String generatedURL = generateURL(shortcode_media.getShortcode(), String.valueOf(FIRST), after);
            String nextResult = getJSON(generatedURL);
            if (nextResult != null) {
                JsonElement jsonTree = parser.parse(nextResult);
                if (jsonTree.isJsonObject()) {
                    JsonObject jsonObject = jsonTree.getAsJsonObject();
                    JsonElement data = jsonObject.get("data");

                    if (data.isJsonObject()) {
                        JsonObject dataObject = data.getAsJsonObject();
                        JsonElement shortcode = dataObject.get("shortcode_media");
                        Shortcode_media shortcodeMedia = gson.fromJson(shortcode, Shortcode_media.class);

                        convertToCommentList(shortcodeMedia);
                        // if (shortcodeMedia.getEdge_media_to_comment().getEdges().size() == FIRST) {
                        getNextComments(shortcode_media, shortcodeMedia.getEdge_media_to_comment().getPage_info().getEnd_cursor());
                        //}
                    }
                }
            }
        }

    }


    public String getJSON(String urlString) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(urlString);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod(REQUEST_METHOD_GET);
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                default:
                    BufferedReader brError = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    StringBuilder sbError = new StringBuilder();
                    String lineError;
                    while ((lineError = brError.readLine()) != null) {
                        sbError.append(lineError + "\n");
                    }
                    brError.close();
                    return sbError.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private String generateURL(String shortCode, String first, String after) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://www.instagram.com/graphql/query/?query_hash=");
        urlBuilder.append("33ba35852cb50da46f5b5e889df7d159");
        urlBuilder.append("&variables={\"shortcode\":\"");
        urlBuilder.append(shortCode);
        urlBuilder.append("\",\"first\":");
        urlBuilder.append(first);
        urlBuilder.append(",\"after\":\"");
        urlBuilder.append(after);
        urlBuilder.append("\"}");
        return urlBuilder.toString();
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        CommentList commentList = CommentList.getInstance();
        commentList.setComments(comments);
        Intent intent = new Intent(MainActivity.getMainContext(), DrawActivity.class);
        MainActivity.getMainContext().startActivity(intent);
        if (record) {
            ScreenRecordingService.prepareRecording();
            ScreenRecordingService.mVirtualDisplay = getVirtualDisplay();
            ScreenRecordingService.mMediaRecorder.start();
        }
    }

    private VirtualDisplay getVirtualDisplay() {
        int screenDensity = ScreenRecordingService.mDisplayMetrics.densityDpi;
        int width = ScreenRecordingService.mDisplayMetrics.widthPixels;
        int height = ScreenRecordingService.mDisplayMetrics.heightPixels;

        return ScreenRecordingService.mMediaProjection.createVirtualDisplay(this.getClass().getSimpleName(),
                width, height, screenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                ScreenRecordingService.mMediaRecorder.getSurface(), null /*Callbacks*/, null /*Handler*/);
    }

}
