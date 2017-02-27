package com.map.mapmaxv1.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.map.mapmaxv1.R;

public class ListActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecycleView;
    private RecyclerViewAdapter mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_profile);

        mToolbar = (Toolbar)findViewById(R.id.anim_toolbar);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(getString(R.string.numbers));

        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mGridLayoutManager);
        mAdapter = new RecyclerViewAdapter(getResources().getStringArray(R.array.numbers));
        mRecycleView.setAdapter(mAdapter);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.z_9dc940eb);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getLightMutedColor(getColor(R.color.primaryColorDark));
                mCollapsingToolbarLayout.setContentScrimColor(mutedColor);
            }
        });
    }
}
