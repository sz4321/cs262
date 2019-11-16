package edu.calvin.cs262.stz4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private final String[] protocols = {"http://", "https://"};
    private TextView showURL;
    private EditText enterURL;
    private Spinner protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterURL = findViewById(R.id.editText);

        protocol = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, protocols);
        protocol.setAdapter(adapter);

        showURL = findViewById(R.id.textView2);
    }

    public void searchURL(View view) {
        //convert the entry to string
        String queryString = enterURL.getText().toString();
        String protocolEntry = protocol.getSelectedItem().toString();
        String protocol_url = protocolEntry.concat(queryString);

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0 && protocolEntry.length() != 0) {

            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString_1", protocol_url);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

            showURL.setText(R.string.loading);
        } else {
            if (queryString.length() == 0) {
                showURL.setText("");
            } else {
                showURL.setText(R.string.make_sure_you_have_internet_connection);
            }
        }
    }

    @NonNull
    @Override
    //when you instantiate your loader
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String urlString = "";
        if (args != null) {
            urlString = args.getString("queryString_1");
        }
        return new URLLoader(this, urlString);
    }

    @Override
    //called when loader's task is finished
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        String sourceCode = data;
        if (sourceCode != null) {
            showURL.setText(sourceCode);
        } else {
            showURL.setText("");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }


}
