package com.georgestudenko.newsapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.georgestudenko.newsapp.BuildConfig;
import com.georgestudenko.newsapp.MainActivity;
import com.georgestudenko.newsapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.R.attr.apiKey;

/**
 * Created by george on 30/04/2017.
 */

    public class NetworkUtils {

        private final static String secureScheme="https";
        private final static String host = "content.guardianapis.com";
        private final static String type="search";
        private final static String query="q";
        private final static String searchTerm="recipe";
        private final static String apiKeyParam="api-key";
        private final static String testApiKey="test";

        public static String getResponseFromHttpUrl(URL url, Context context) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            switch (responseCode){
                case 200:
                case 201:
                case 202:
                case 203:
                    break;
                case 204:
                    MainActivity.setErrorMessage(context.getString(R.string.e204),true);
                    return null;
                case 400:
                    MainActivity.setErrorMessage(context.getString(R.string.e400),true);
                    return null;
                case 401:
                    MainActivity.setErrorMessage(context.getString(R.string.e401),true);
                    return null;
                case 403:
                    MainActivity.setErrorMessage(context.getString(R.string.e403),true);
                    return null;
                case 404:
                    MainActivity.setErrorMessage(context.getString(R.string.e404),true);
                    return null;
                case 408:
                    MainActivity.setErrorMessage(context.getString(R.string.e408),true);
                    return null;
                case 409:
                    MainActivity.setErrorMessage(context.getString(R.string.e409),true);
                    return null;
                case 429:
                    MainActivity.setErrorMessage(context.getString(R.string.e429),true);
                    return null;
                case 444:
                    MainActivity.setErrorMessage(context.getString(R.string.e444),true);
                    return null;
                case 500:
                    MainActivity.setErrorMessage(context.getString(R.string.e500),true);
                    return null;
                case 502:
                    MainActivity.setErrorMessage(context.getString(R.string.e502),true);
                    return null;
                case 503:
                    MainActivity.setErrorMessage(context.getString(R.string.e503),true);
                    return null;
                case 504:
                    MainActivity.setErrorMessage(context.getString(R.string.e504),true);
                    return null;
            }

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

        public static boolean isConnected(Context context) {
            ConnectivityManager
                    cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
        }

        public static URL buildUrl(){
            URL URL= null;
            Uri.Builder builder= new Uri.Builder();
            builder.scheme(secureScheme)
                    .authority(host)
                    .appendPath(type)
                    .appendQueryParameter(query, searchTerm);
                    builder.appendQueryParameter(apiKeyParam, testApiKey);
            builder.build();
            try {
                URL = new URL(builder.build().toString());
            }catch (MalformedURLException ex){
                System.out.println("ERROR PARSING URL: ");
                ex.printStackTrace();
            }
            return URL;
        }
}
