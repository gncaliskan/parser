package com.instagram_parser.Request;

import android.os.AsyncTask;

import com.instagram_parser.MainActivity;
import com.instagram_parser.Util.SharedUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class RequestInstagramApi extends AsyncTask<String, Void, String> {


    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;


    @Override
    protected String doInBackground(String... params) {
        String result = null;
        String inputLine;
        try {
            HttpURLConnection connection;
            connection = createGetConnection(params[0]);
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());

                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }

                reader.close();
                streamReader.close();

                result = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    public HttpURLConnection createGetConnection(String stringUrl) throws IOException {
        URL myUrl = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.connect();
        return connection;
    }


}
