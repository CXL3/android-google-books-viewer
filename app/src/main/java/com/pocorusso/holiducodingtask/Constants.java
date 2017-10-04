package com.pocorusso.holiducodingtask;


public class Constants {
    //TODO Put the API_KEY in gradle file so we can build different flavor of the app with different api KEYS
    //also to protect the API from exposure
    protected static final String API_KEY = "FILL_IN_THE_KEY";
    //Hard coded search query as per task description
    protected static final String API_QUERY = "https://www.googleapis.com/books/v1/volumes?q=dogs&maxResults=40&key=" + API_KEY;


    //Example JSON: https://www.googleapis.com/books/v1/volumes/_ojXNuzgHRcC
    //Tags for JSON response
    public static final String TAG_ITEMS = "items";
    public static final String TAG_VOLUME_INFO = "volumeInfo";
    public static final String TAG_TITLE = "title";
    public static final String TAG_IMAGE_LINKS = "imageLinks";
    public static final String TAG_THUMBNAIL = "thumbnail";

}
