package com.map.mapmaxv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.map.mapmaxv1.dto.MarkDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<MarkDTO> mark= new ArrayList<>();
    private LatLng curMark;
    static final private int resultOfCreate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);                                                     /**создание карты */

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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        justTest(mMap);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == resultOfCreate)
            if (resultCode == RESULT_OK){
                MarkDTO markDTO = new MarkDTO();
                markDTO.setLat(curMark.latitude);
                markDTO.setLng(curMark.longitude);
                markDTO.setTitle(data.getStringExtra("Title"));
                markDTO.setText(data.getStringExtra("Text"));
                markDTO.setPrice(Integer.parseInt(data.getStringExtra("Price")));
                markDTO.setDate(new Date());
                mark.add(markDTO);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(curMark);
                markerOptions.title(markDTO.getTitle());
                mMap.addMarker(markerOptions);
            }
    }

    public void justTest(GoogleMap googleMap){
        mMap = googleMap;
        mMap.getUiSettings().isCompassEnabled();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                curMark=latLng;

                Intent intent = new Intent(MapsActivity.this, MarkerActivity.class);
                startActivityForResult(intent, resultOfCreate);                                                /** запуск активити создания маркера */
            }
        });

    }

}
