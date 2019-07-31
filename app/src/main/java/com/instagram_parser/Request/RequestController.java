package com.instagram_parser.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.instagram_parser.Entity.Root;

import java.util.concurrent.ExecutionException;

public class RequestController {

    public static Root getRoot(String url) {
        String result;

        RequestInstagramApi getRequest = new RequestInstagramApi();
        try {
            result = getRequest.execute(url).get();
            Gson gson = new GsonBuilder().create();
            Root dto = gson.fromJson(result, Root.class);
            if (dto != null) {
                return dto;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
