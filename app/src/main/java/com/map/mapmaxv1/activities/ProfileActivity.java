package com.map.mapmaxv1.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.map.mapmaxv1.CircularTransformation;
import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.MarkDTO;
import com.map.mapmaxv1.dto.UserDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecycleView;
    private RecyclerViewAdapterProfile mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    private List<UserDTO> UserDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_profile);

        mToolbar = (Toolbar)findViewById(R.id.anim_toolbar);
        setSupportActionBar(mToolbar);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(getString(R.string.numbers));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.z_9dc940eb);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getLightMutedColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimaryDark));
                mCollapsingToolbarLayout.setContentScrimColor(mutedColor);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_profile);
        navigationView.setNavigationItemSelectedListener(this);

//        mRecycleView = (RecyclerView)findViewById(R.id.recyclerView);
//        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        mRecycleView.setItemAnimator(new DefaultItemAnimator());
//        mRecycleView.setLayoutManager(mGridLayoutManager);
//        mAdapter = new RecyclerViewAdapterProfile(UserDTO);
//        mRecycleView.setAdapter(mAdapter);

//        ImageView imageView = (ImageView)findViewById(R.id.imageView3);
//        Picasso.with(this)
//                .load(R.drawable.z_9dc940eb)
//                .transform(new CircularTransformation(0))
//                .into(imageView);

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
            case R.id.nav_city:
                intent = new Intent(ProfileActivity.this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_age:
                intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_job_place:
                intent = new Intent(ProfileActivity.this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_type_job:
                intent = new Intent(ProfileActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_shcool:
                //fragmentClass = FragmentPhoto.class;
                intent = new Intent(ProfileActivity.this, FollowActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_about:
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
