package com.example.persistence.providers.contracts;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by H_P on 2016/5/3.
 *
 * @author luo
 * @version 1.0
 */
public class MovieContract {
    //database basic information
    public static final String CONTENT_AUTHORITY = "com.example.persistence";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_GENRE = "genre";

    // table movie basic information
    public static final class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_MOVIE;
        //define the table schema
        public static final String TABLE_NAME = "movieTable";
        public static final String COLUMN_MOVIE_NAME = "movieName";
        public static final String COLUMN_MOVIE_GENRE = "movieGenre";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "movieReleaseDate";

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
    //table movie basic information
    public static final class GenreEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/"+CONTENT_URI+"/"+PATH_GENRE;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/"+CONTENT_URI+"/"+PATH_GENRE;
        //define the table schema
        public static final String TABLE_NAME = "genreTable";
        public static final String COLUMN_GENRE_NAME = "genreName";

        public static Uri buildMovieUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }

}
