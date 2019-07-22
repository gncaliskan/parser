package com.instagram_parser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.instagram_parser.Model.Shortcode_media;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;


public class MainActivity extends Activity {

    private Button titleButton, descButton, logoButton, yazarlarButton;
    LinearLayout title_layout, desc_layout, logo_layout, yazarlar_layout;
    private ProgressDialog progressDialog;
    private static String URL = "https://www.instagram.com/p/B0EKAEBFye0";
    private static String baseUrl = "http://www.mobilhanem.com/";
    private static String authorUrl = "https://www.mobilhanem.com/ekibimiz/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleButton = (Button)findViewById(R.id.buttonTitle);
        descButton = (Button)findViewById(R.id.buttonDesc);
        logoButton = (Button)findViewById(R.id.buttonImage);
        yazarlarButton = (Button)findViewById(R.id.buttonYazarlar);

        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    new FetchTitle().execute(); // başlık çekmek için
                }
            }
        });

        descButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    new FetchDescription().execute(); // açıklama çekmek için
                }
            }
        });

        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    new FetchImageLogo().execute();  // logo çekmek için
                }
            }
        });

        yazarlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    new FetchYazarlar().execute();    // yazarlar kısmını çekmek için
                }
            }
        });

    }
    private class FetchTitle extends AsyncTask<Void, Void, Void> {

        String title;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("BAŞLIK");
            progressDialog.setMessage("Başlık Çekiliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(URL).get();    // web siteye bağlantıyı gerçeleştirme

                title = doc.title();  // ilgili sayfanın başlığını almak için

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            title_layout = (LinearLayout)findViewById(R.id.title_layout);
            TextView txt_title = (TextView)findViewById(R.id.txt_title);
            title_layout.setVisibility(View.VISIBLE);
            txt_title.setText("Title: " + "" + title);
            progressDialog.dismiss();
        }
    }

    private class FetchDescription extends AsyncTask<Void, Void, Void> {

        String desc;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("AÇIKLAMA");
            progressDialog.setMessage("Açıklama Çekiliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(URL).get();
                Elements elements = doc.select("meta[name=description]");  // ilgili sayfanın açıklamasını almak için
                desc = elements.attr("content");


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            desc_layout = (LinearLayout)findViewById(R.id.desc_layout);
            TextView txt_desc = (TextView)findViewById(R.id.txt_desc);
            desc_layout.setVisibility(View.VISIBLE);
            txt_desc.setText("Description: " + "" + desc);
            progressDialog.dismiss();
        }
    }

    private class FetchImageLogo extends AsyncTask<Void, Void, Void> {

        Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("LOGO");
            progressDialog.setMessage("Logo Çekiliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(URL).get();
                Elements elements = doc.select("img[src]");
                String imgSrc = elements.attr("src");
                InputStream input = new java.net.URL(imgSrc).openStream();
                bitmap = BitmapFactory.decodeStream(input);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            logo_layout = (LinearLayout)findViewById(R.id.logo_layout);
            ImageView img_logo = (ImageView)findViewById(R.id.img_logo);
            logo_layout.setVisibility(View.VISIBLE);
            img_logo.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    }

    private class FetchYazarlar extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("YAZARLAR");
            progressDialog.setMessage("Yazarlar Çekiliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(URL).get();

                StringBuilder htmlString = new StringBuilder();
                htmlString.append(doc.outerHtml());
                String subHtml = htmlString.substring(htmlString.indexOf("window._sharedData = ")+21);
                String sharedData = subHtml.substring(0, subHtml.indexOf(";</script> "));
                JsonParser parser = new JsonParser();

                JsonElement jsonTree = parser.parse(sharedData);
                if(jsonTree.isJsonObject()){
                    JsonObject jsonObject = jsonTree.getAsJsonObject();
                    JsonElement entry_data = jsonObject.get("entry_data");

                    if(entry_data.isJsonObject()){
                        JsonObject entry_dataObject = entry_data.getAsJsonObject();
                        JsonElement postPage = entry_dataObject.get("PostPage");

                        if(postPage.isJsonArray()){
                            JsonArray postPageArray = postPage.getAsJsonArray();
                            JsonObject postPageObject = (JsonObject) postPageArray.get(0);
                            JsonElement graphql = postPageObject.get("graphql");

                            if(graphql.isJsonObject()){
                                JsonObject graphqlObject = graphql.getAsJsonObject();

                                JsonElement shortcode_media = graphqlObject.get("shortcode_media");
                                Gson gson = new Gson();
                                Shortcode_media shortcodeMedia = gson.fromJson(shortcode_media, Shortcode_media.class);
                            }
                        }
                    }

                }



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            yazarlar_layout = (LinearLayout)findViewById(R.id.yazarlar_layout);
            TextView txt_yazarlar = (TextView)findViewById(R.id.txt_yazarlar);
            yazarlar_layout.setVisibility(View.VISIBLE);
            txt_yazarlar.setText(authors);
            progressDialog.dismiss();
        }
    }

    // HTML page
    static final String BLOG_URL = "https://xjaphx.wordpress.com/";
    // XPath query
    static final String XPATH_STATS = "//div[@id='blog-stats']/ul/li";


    /*
     * get blog statistics
     */
    public String getBlogStats() throws Exception {
        String stats = "";

        // config cleaner properties
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        CleanerProperties props = htmlCleaner.getProperties();
        props.setAllowHtmlInsideAttributes(false);
        props.setAllowMultiWordAttributes(true);
        props.setRecognizeUnicodeChars(true);
        props.setOmitComments(true);

        // create URL object
        java.net.URL url = new URL(BLOG_URL);
        // get HTML page root node
        TagNode root = htmlCleaner.clean(url);

        // query XPath
        Object[] statsNode = root.evaluateXPath(XPATH_STATS);
        // process data if found any node
        if(statsNode.length > 0) {
            // I already know there's only one node, so pick index at 0.
            TagNode resultNode = (TagNode)statsNode[0];
            // get text data from HTML node
            stats = resultNode.getText().toString();
        }

        // return value
        return stats;
    }
}