package com.map.mapmaxv1.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.map.mapmaxv1.CircularTransformation;
import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.MarkDTO;
import com.map.mapmaxv1.dto.UserDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

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

        mRecycleView = (RecyclerView)findViewById(R.id.recyclerView);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mGridLayoutManager);
        mAdapter = new RecyclerViewAdapterProfile(UserDTO);
        mRecycleView.setAdapter(mAdapter);

//        ImageView imageView = (ImageView)findViewById(R.id.imageView3);
//        Picasso.with(this)
//                .load(R.drawable.z_9dc940eb)
//                .transform(new CircularTransformation(0))
//                .into(imageView);

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
