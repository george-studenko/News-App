package com.georgestudenko.newsapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.georgestudenko.newsapp.Data.NewsAdapter;
import com.georgestudenko.newsapp.Data.NewsLoader;
import com.georgestudenko.newsapp.Models.News;
import com.georgestudenko.newsapp.Utils.NetworkUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<News>>{

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
        mMessageText = (TextView) findViewById(R.id.messageText);
        mListView = (ListView) findViewById(R.id.newssListView);
        mListView.setEmptyView(mMessageText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }

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

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        mMessageText.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        mMessageText.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);

        if(data !=null) {
            mAdapter = new NewsAdapter(this, data);
            mListView.setAdapter(mAdapter);
        }
        if(mShowError){
            mMessageText.setText(errorMessage);
            mShowError=false;
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        clearAdapter();
    }
}
