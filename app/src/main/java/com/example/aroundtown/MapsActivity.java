package com.example.aroundtown;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/*
        Toast.makeText(this, "It Works 2", Toast.LENGTH_LONG).show();
*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        /*Toast.makeText(this, "It Works 3", Toast.LENGTH_LONG).show();*/

        LatLng Lubbock = new LatLng(33.577816, -101.870596);
//googleMap.addMarker(new MarkerOptions().position(Lubbock).title("Marker in Lubbock"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lubbock,12));

        LatLng MadHatters = new LatLng(33.581287,-101.845452);
        googleMap.addMarker(new MarkerOptions().position(MadHatters).title("Mad Hatter's House of Games").alpha(.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        LatLng TTUFootball = new LatLng(33.591087,-101.872902);
        googleMap.addMarker(new MarkerOptions().position(TTUFootball).title("Texas Tech Football").alpha(.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng Presentation = new LatLng(33.583123,-101.873245);
        googleMap.addMarker(new MarkerOptions().position(Presentation).title("Around Town Presentation").alpha(.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        LatLng LubbockPanicRoom = new LatLng(33.527987,-101.889096);
        googleMap.addMarker(new MarkerOptions().position(LubbockPanicRoom).title("Lubbock Panic Room").alpha(.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        LatLng MainEventLubbock = new LatLng(33.551821,-101.947097);
        googleMap.addMarker(new MarkerOptions().position(MainEventLubbock).title("Main Event").alpha(.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }



}
