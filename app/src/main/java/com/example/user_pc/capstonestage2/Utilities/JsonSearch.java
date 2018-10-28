package com.example.user_pc.capstonestage2.Utilities;

import android.content.Context;

import com.example.user_pc.capstonestage2.Models.Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class JsonSearch {

    public static ArrayList<Search> getSearchStringsFromJson(Context context, String searchJsonString)
            throws JSONException {

        final String RESULTS = "results";
        final String URL = "url";
        final String IMAGE_URL = "image_url";
        final String TITLE = "title";
        final String SYNOPSIS = "synopsis";
        final String TYPE = "type";
        final String EPISODES = "episodes";
        final String SCORE = "score";

        ArrayList<Search> parsedSearchData = new ArrayList<Search>();

        JSONObject searchObject = new JSONObject(searchJsonString);
        JSONArray searchArray = searchObject.getJSONArray(RESULTS);

        for (int i = 0; i < searchArray.length(); i++) {
            String url;
            String imageUrl;
            String title;
            String synopsis;
            String type;
            int episodes;
            double score;

            searchObject = searchArray.getJSONObject(i);

            url = searchObject.getString(URL);
            imageUrl = searchObject.getString(IMAGE_URL);
            title = searchObject.getString(TITLE);
            synopsis = searchObject.getString(SYNOPSIS);
            type = searchObject.getString(TYPE);
            episodes = searchObject.getInt(EPISODES);
            score = searchObject.getDouble(SCORE);

            parsedSearchData.add(new Search(url, imageUrl, title, synopsis, type, episodes, score));

        }

        return parsedSearchData;
    }

}
