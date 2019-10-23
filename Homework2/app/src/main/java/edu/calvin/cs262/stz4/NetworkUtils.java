package edu.calvin.cs262.stz4;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    static String getURLInfo(String urlString){
        HttpURLConnection urlConnection_1 = null;
        BufferedReader reader = null;
        String final_string = "";

        try{

            URL new_url = new URL(urlString);
            Log.e(NetworkUtils.class.getSimpleName(), new_url.toString());

            HttpURLConnection urlConnection = (HttpURLConnection) new_url.openConnection();
            urlConnection.connect();
            Log.e(NetworkUtils.class.getSimpleName(), urlString);


            // Get the InputStream
            InputStream inputStream = urlConnection.getInputStream();


            //create a buffered reader from that input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Use a stringbuilder to hold the incoming response
            StringBuilder builder = new StringBuilder();

            //read the input line-by-line
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            inputStream.close();
            final_string = builder.toString();

            if (final_string.length() == 0) {
                // Stream was empty.
                return null;
            }


        } catch (IOException e){
            return "error...";
        } finally {
            if (urlConnection_1 != null) {
                urlConnection_1.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "another error...";
                }
            }
        }

        return final_string;
    }
}
