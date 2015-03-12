package app.com.feldmansoft.android.sunshine;

import android.location.Location;
import android.net.Uri;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by af250127 on 12/03/2015.
 */
public class ForecastRetriever {

    public Forecast getForecastData(Location currentLocation,ForecastFragment.Units units ) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        Forecast weatherData;
        try {
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.scheme("http")
                    .authority("api.openweathermap.org")
                    .appendPath("data")
                    .appendPath("2.5")
                    .appendPath("forecast")
                    .appendPath("daily")
                    .appendQueryParameter("lat", "" + currentLocation.getLatitude())
                    .appendQueryParameter("lon", "" + currentLocation.getLongitude())
                    .appendQueryParameter("mode", "json")
                    .appendQueryParameter("units", units.toString())
                    .appendQueryParameter("cnt", "7");


            String uri = uriBuilder.build().toString();
            URL url = new URL(uri);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            forecastJsonStr = buffer.toString();
            GsonBuilder builder = new GsonBuilder();
            weatherData = builder.create().fromJson(forecastJsonStr, Forecast.class);
            Log.i("Froecast", weatherData.toString());
        } catch (IOException e) {
            Log.e("ForecastFragment", "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("ForecastFragment", "Error closing stream", e);
                }
            }
        }
        return weatherData;
    }
}
