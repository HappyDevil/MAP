package com.map.mapmaxv1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.UserDTO;

import java.util.List;

public class FollowActivity extends AppCompatActivity {

    private RecyclerView mRecycleView1;
    private RecyclerView mRecycleView2;
    private RecyclerViewAdapterFollow mAdapter1;
    private StaggeredGridLayoutManager mGridLayoutManager1;
    private RecyclerViewAdapterComment mAdapter2;
    private StaggeredGridLayoutManager mGridLayoutManager2;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mRecycleView1 = (RecyclerView)findViewById(R.id.rv1);
        mRecycleView2 = (RecyclerView)findViewById(R.id.rv2);

        mGridLayoutManager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView1.setItemAnimator(new DefaultItemAnimator());
        mRecycleView1.setLayoutManager(mGridLayoutManager1);
        mAdapter1 = new RecyclerViewAdapterFollow(list);
        mRecycleView1.setAdapter(mAdapter1);

        mGridLayoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView2.setItemAnimator(new DefaultItemAnimator());
        mRecycleView2.setLayoutManager(mGridLayoutManager2);
        mAdapter2 = new RecyclerViewAdapterComment(list);
        mRecycleView2.setAdapter(mAdapter2);
    }
}
