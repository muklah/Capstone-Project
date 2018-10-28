package com.example.user_pc.capstonestage2.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkSearch {

    final static String SEARCH_BASE_URL = "https://api.jikan.moe/v3/search/";
    final static String TYPE = "anime";
    final static String Q = "q";
    final static String PAGE = "page";
    final static String PAGE_NUMBER = "1";

    public static URL buildUrl(String searchQuery) {
        Uri builtUri = Uri.parse(SEARCH_BASE_URL).buildUpon()
                .appendPath(TYPE)
                .appendQueryParameter(Q,searchQuery)
                .appendQueryParameter(PAGE,PAGE_NUMBER)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
