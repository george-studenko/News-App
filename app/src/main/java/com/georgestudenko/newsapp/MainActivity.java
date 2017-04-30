package com.georgestudenko.newsapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
import com.georgestudenko.newsapp.Data.NewsAdapter;
import com.georgestudenko.newsapp.Data.NewsLoader;
import com.georgestudenko.newsapp.Models.News;
import com.georgestudenko.newsapp.Utils.NetworkUtils;

import java.util.List;
    private static final int LOADER_ID = 182;
    private static String errorMessage;
    private static boolean mShowError;
    private static TextView mMessageText;
    private NewsAdapter mAdapter;
    private ListView mListView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    public static void setErrorMessage(String errorMessageToSet, boolean showErrorOnLoadFinish) {
        errorMessage = errorMessageToSet;
        if(showErrorOnLoadFinish){
            mShowError =true;
        }else {
            mMessageText.setText(errorMessage);
        }
    }

    private void clearAdapter() {
        if(mAdapter!=null) {
            mAdapter.clear();
            mAdapter.setNotifyOnChange(true);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkInternetConnection();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkInternetConnection();
    }

    private void checkInternetConnection() {
        if(!NetworkUtils.isConnected(this)){
            clearAdapter();
            setErrorMessage(getString(R.string.no_internet),false);
        }
    }
}
