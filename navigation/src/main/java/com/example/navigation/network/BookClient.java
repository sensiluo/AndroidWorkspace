package com.example.navigation.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by H_P on 2016/4/24.
 * @autho luo
 * @version 1.0
 * act as  OpenLibrary API client for sending out network requests to specific endpoints
 */
public class BookClient  {
    private static final String API_BASE_URL="https://openlibrary.org/";
    private AsyncHttpClient client;

    public BookClient(){
        this.client = new AsyncHttpClient();
    }

    private String getApiBaseUrl(String relativeUrl){
        return API_BASE_URL+relativeUrl;
    }

    // method for accessing the api search
    public void getBooks(final String query,JsonHttpResponseHandler handler){
        try {
            String url = getApiBaseUrl("search.json?q=");
            client.get(url+ URLEncoder.encode(query,"utf-8"),handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    //method for accessing the book detail information
    public void getExtraBookDetails(String openLibraryId,JsonHttpResponseHandler handler){
        String url = getApiBaseUrl("books/");
        client.get(url+openLibraryId+".json",handler);
    }

}
