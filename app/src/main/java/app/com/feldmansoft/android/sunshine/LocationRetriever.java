package app.com.feldmansoft.android.sunshine;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
* Created by af250127 on 12/03/2015.
*/
public class LocationRetriever implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private GoogleApiClient _googleApiClient;
    private Location foundLocation = new Location("");

    private ConditionVariable locker = new ConditionVariable(false);
    private Context context;

    public LocationRetriever(Context context)
    {

        this.context = context;
    }




    public Location GetLocation() {
        buildGoogleApiClient(context);
        _googleApiClient.connect();
        Log.i("APP.Location", "Waiting for location");
        locker.block(10000);
        Log.i("APP.Location","Resuming for location");
        return foundLocation;
    }


    protected synchronized void buildGoogleApiClient(Context context ) {
        _googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        foundLocation = LocationServices.FusedLocationApi.getLastLocation(
                _googleApiClient);
        Log.i("APP.Location","Location is "+foundLocation.toString());
        locker.open();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
}
