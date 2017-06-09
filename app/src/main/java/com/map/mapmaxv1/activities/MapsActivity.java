package com.map.mapmaxv1.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.map.mapmaxv1.CircularTransformation;
import com.map.mapmaxv1.MainActivity;
import com.map.mapmaxv1.MapApp;
import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.DaoSession;
import com.map.mapmaxv1.dto.MarkDTO;
import com.map.mapmaxv1.dto.MarkDTODao;
import com.map.mapmaxv1.dto.UserDTO;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.sqrt;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

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
    private UserDTO user;
    private List<MarkDTO> markchoose;
    private DaoSession daoSession;

    private ClusterManager<MarkDTO> clusterManager;

    private boolean type = true;
    private MarkDTODao markDAO;
    private Context context;
    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_MARKER_LIMIT = "marker_limit";
    public static final String APP_PREFERENCES_TYPE = "type";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); /**создание карты */

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        context = this;

        user = new UserDTO();

        user.setUsername("User");

        user.setFIO("My FIO");

        user.setRole("Role");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == true){
                    Snackbar.make(view, "Теперь вы модель", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    type = false;
                }else {
                    Snackbar.make(view, "Теперь вы мастер", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    type = true;
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        daoSession = ((MapApp)getApplication()).getDaoSession();
        markDAO = daoSession.getMarkDTODao();

         //Тут должна быть попытка конекшена

        mark = markDAO.loadAll();// Считали данные из БД
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        clusterManager = new ClusterManager<>(this.getApplicationContext(), mMap);

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);


        justTest(mMap);
        showMarks(mark);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains(APP_PREFERENCES_MARKER_LIMIT))
        if (mSettings.contains(APP_PREFERENCES_TYPE)){
            // Получаем число из настроек
            markerLimit = mSettings.getInt(APP_PREFERENCES_MARKER_LIMIT, 0);
            type = mSettings.getBoolean(APP_PREFERENCES_TYPE, false);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_MARKER_LIMIT, markerLimit);
        editor.putBoolean(APP_PREFERENCES_TYPE, type);
        editor.apply();
    }

    public void showMarks(List<MarkDTO> mark)
    {
        for(MarkDTO m : mark)
        {
                clusterManager.addItem(m);
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
                markDTO.setUser(user.getUsername());
                markDTO.setFIO(user.getFIO());
                markDTO.__setDaoSession(daoSession);

                boolean b=Boolean.valueOf(data.getStringExtra("CheckData"));

                markDAO.insert(markDTO);
                mark.add(markDTO);

                MarkerOptions markerOptions = new MarkerOptions();                           /** объявление объекта настроек маркера */
                markerOptions.position(curMark);
                markerOptions.title(markDTO.getTitle());

                clusterManager.addItem(markDTO);

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

                    Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                        addressMark = address.get(0).getAddressLine(1) + ", " + address.get(0).getAddressLine(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(MapsActivity.this, MarkerActivity.class);
                    intent.putExtra("Address", addressMark);
                    startActivityForResult(intent, resultOfCreate);                          /** запуск активити создания маркера */


                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Вы достигли лимита маркеров (Максимум: 5)", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener() {
            @Override
            public boolean onClusterClick(Cluster cluster) {
                markchoose= (List<MarkDTO>) cluster.getItems();

                Intent intent = new Intent(MapsActivity.this, ListActivity.class);
                intent.putParcelableArrayListExtra("MarkList", (ArrayList<? extends Parcelable>) markchoose);
                startActivity(intent);

                return false;
            }
        });
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MarkDTO>() {
            @Override
            public boolean onClusterItemClick(MarkDTO markDTO) {
                markchoose.clear();
                markchoose.add(markDTO);

                Intent intent = new Intent(MapsActivity.this, ListActivity.class);
                intent.putParcelableArrayListExtra("MarkList", (ArrayList<? extends Parcelable>) markchoose);
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        Fragment fragment = null;
//        Class fragmentClass = null;

        Intent intent;
        switch(id)
        {
            case R.id.nav_camera:
                intent = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gallery:
                intent = new Intent(MapsActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_adds:
                intent = new Intent(MapsActivity.this, ListActivity.class);
                intent.putParcelableArrayListExtra("MarkList", (ArrayList<? extends Parcelable>) mark);
                startActivity(intent);
                break;
            case  R.id.nav_slideshow:
                intent = new Intent(MapsActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_manage:
                //fragmentClass = FragmentPhoto.class;
                intent = new Intent(MapsActivity.this, FollowActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_search:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.container_all, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}