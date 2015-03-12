package app.com.feldmansoft.android.sunshine;

/**
 * Created by af250127 on 10/03/2015.
 */

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;


/**
 * A placeholder fragment containing a simple view.
 */
public  class ForecastFragment extends Fragment  {

    private ArrayAdapter<String> _forecastAdapter;

    enum Units
    {
        metric,
        imperial
    }

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            FetchWeatherTask locationTask = new FetchWeatherTask(Units.metric);
            locationTask.execute(getActivity());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        LinkedList<String> data = new LinkedList<String>();


       _forecastAdapter = new ArrayAdapter<String>(
                getActivity(), // The current context (this activity)
                R.layout.list_item_forecast, // The name of the layout ID.
                R.id.list_item_forecast_listview, // The ID of the textview to populate.
                data );

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(_forecastAdapter);

        return rootView;
    }


    public class FetchWeatherTask extends AsyncTask<Context,Void,Forecast>
    {
        private Units units;

        public FetchWeatherTask(Units units)
        {

            this.units = units;
        }
        @Override
        protected Forecast doInBackground(Context... params) {
            LocationRetriever locationRetriever = new LocationRetriever(params[0]);
            Location currentLocation = locationRetriever.GetLocation();
            ForecastRetriever forecastRetriever = new ForecastRetriever();
            Forecast forecastData = forecastRetriever.getForecastData(currentLocation, units);
            return forecastData;
        }

        @Override
        protected void onPostExecute(Forecast forecast) {
            if(forecast != null)
            {
                _forecastAdapter.clear();
                LinkedList<String> forecastStrings = new LinkedList<>();
                for(List daysResult :forecast.getList())
                {
                    forecastStrings.add(daysResult.toString());
                }
                _forecastAdapter.addAll(forecastStrings);
            }
            super.onPostExecute(forecast);
        }
    }
}
