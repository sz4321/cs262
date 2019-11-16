package edu.calvin.cs262.stz4;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

class URLLoader extends AsyncTaskLoader<String> {

    private final String mURLString;

    public URLLoader(Context context, String urlString) {
        super(context);
        mURLString = urlString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getURLInfo(mURLString);
    }
}
