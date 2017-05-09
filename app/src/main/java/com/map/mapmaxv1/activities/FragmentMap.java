package com.map.mapmaxv1.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.map.mapmaxv1.R;
import com.map.mapmaxv1.db.MarkHelper;
import com.map.mapmaxv1.dto.MarkDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static java.lang.Math.sqrt;

/**
 * Created by User on 20.04.2017.
 */

public class FragmentMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<MarkDTO> mark= new ArrayList<>();
    private LatLng curMark;
    private int markerLimit = 0;
    static final private int resultOfCreate = 0;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private boolean visible=true;
    private String addressMark = null;
    private Marker oneMarker;
    private List<MarkDTO> markchoose;
    private boolean markersize = true;
    private MarkHelper db;

    //@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_maps, container, false);
//        return view;
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this); /**создание карты */
//
//        db = new MarkHelper(this); /** Создали помощника и открыли подключение к DB */
//        db.openConnection(db.getWritableDatabase());
//
//        /** Тут должна быть попытка конекшена */
//        mark = db.readMark(null,null,null,null,null,null);/** Считали данные из БД */
//
//        db.close();
        return null;
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        justTest(mMap);
        showMarks(mark);
    }

    public void showMarks(List<MarkDTO> mark)
    {
        for(MarkDTO m : mark)
        {
            if(m.isVisible())
            {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(m.getLat(),m.getLng()));
                markerOptions.title(m.getTitle());
                oneMarker = mMap.addMarker(markerOptions);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){           /** ждет данные, которые должны прийти с активности с номером 0 */
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == resultOfCreate)
            if (resultCode == RESULT_OK){
                MarkDTO markDTO = new MarkDTO();
                markDTO.setLat(curMark.latitude);
                markDTO.setLng(curMark.longitude);
                markDTO.setTitle(data.getStringExtra("Title"));
                markDTO.setType(data.getStringExtra("Type"));
                markDTO.setText(data.getStringExtra("Text"));
                markDTO.setPrice(Integer.parseInt(data.getStringExtra("Price")));
                markDTO.setDate(new Date());
                markDTO.setVisible(visible);
                if (data.getStringExtra("CheckData").equals("true"))  /** проверяет в какую базу данных загружать маркер, локальный он будет или нет */
                {
                    db.openConnection(db.getWritableDatabase());
                    int id=db.writeMark(markDTO,false); /** добавление маркера в локальную базу данных */
                    markDTO.setId(id);
                    mark.add(markDTO);
                    db.close();
                }
                else
                {
                    mark.add(markDTO);
                }

                MarkerOptions markerOptions = new MarkerOptions();                           /** объявление объекта настроек маркера */
                markerOptions.position(curMark);
                markerOptions.title(markDTO.getTitle());

                if(visible) oneMarker = mMap.addMarker(markerOptions);                          /** созданному маркеру присвоили свою переменную его класса, на всякий */
                visible = true;
                markerLimit++;
            }
    }

    public void justTest(GoogleMap googleMap){
        mMap = googleMap;
        mMap.getUiSettings().isCompassEnabled();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {              /** слушает долгое нажатие на карте */
        @Override
        public void onMapLongClick(LatLng latLng) {
            if (markerLimit <= 4){                                                       /** ставит ограничение на кол-во маркеров на карте */
                curMark=latLng;                                                          /** передает координаты маркера в глобальную переменную, чтобы их можно было передать в базу данных */


                for (int i=0; i<mark.size(); i++){
                    if(sqrt((latLng.latitude - mark.get(i).getLat())*(latLng.latitude - mark.get(i).getLat())+(latLng.longitude - mark.get(i).getLng())*(latLng.longitude - mark.get(i).getLng()))<0.0007){
                        curMark = new LatLng(mark.get(i).getLat(),mark.get(i).getLng());
                        visible=false;
                        break;
                    }
                }

                Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    addressMark = address.get(0).getAddressLine(1) + ", " + address.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Intent intent = new Intent(MapsActivity.this, MarkerActivity.class);
                //intent.putExtra("Address", addressMark);
                //startActivityForResult(intent, resultOfCreate);                          /** запуск активити создания маркера */


            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Вы достигли лимита маркеров (Максимум: 5)", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        });

        final GoogleMap.InfoWindowAdapter info = new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                ContextThemeWrapper wrapper = new ContextThemeWrapper(getApplicationContext(), R.style.AppTheme);
                LayoutInflater inflater = (LayoutInflater) wrapper.getSystemService(LAYOUT_INFLATER_SERVICE);
                //View layout = inflater.inflate(R.layout.item_list, null);
                View itemView = inflater.inflate(R.layout.item_list, null);
                RecyclerViewAdapterList.ViewHolder viewHolder = new RecyclerViewAdapterList.ViewHolder(itemView);



                return itemView;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        };
        mMap.setInfoWindowAdapter(info);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {                /** метод, слушающий и обрабатывающий нажатия на маркер */
        @Override
        public boolean onMarkerClick(Marker marker) {
            markchoose= new ArrayList<>();

            for(int i=0;i<mark.size();i++)
            {
                if((marker.getPosition().longitude==mark.get(i).getLng())&&(marker.getPosition().latitude==mark.get(i).getLat()))
                {
                    markchoose.add(mark.get(i));
                }
            }
            return false;
        }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Intent intent = new Intent(MapsActivity.this, ListActivity.class);
                //intent.putParcelableArrayListExtra("MarkList", (ArrayList<? extends Parcelable>) markchoose);
                //startActivity(intent);

                marker.hideInfoWindow();
            }
        });
    }
}
