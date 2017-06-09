package com.map.mapmaxv1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TabHost;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.CommentDTO;
import com.map.mapmaxv1.dto.FollowDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 07.05.2017.
 */

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView mRecycleView1;
    private RecyclerView mRecycleView2;
    private RecyclerViewAdapterFollow mAdapter1;
    private StaggeredGridLayoutManager mGridLayoutManager1;
    private RecyclerViewAdapterComment mAdapter2;
    private StaggeredGridLayoutManager mGridLayoutManager2;
    private List<FollowDTO> list;
    private List<CommentDTO> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setTitle("Уведомления");

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Подписки");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Отзывы");
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);

        /**
         *     ЗДЕСЬ ЗАГРУЗКА СПИСКОВ ИЗ БАЗЫ ДАННЫХ
         */

//        CommentDTO a = new CommentDTO();
//        a.setDate(new Date());
//        a.setRait(4);
//        a.setText("test test test test test test test test test test test test test test test");
//        list1.add(a);
//
//        FollowDTO b = new FollowDTO();
//        b.setFIO("Max Crazy");
//        b.setRole("Photographer");
//        list.add(b);

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
        mAdapter2 = new RecyclerViewAdapterComment(list1);
        mRecycleView2.setAdapter(mAdapter2);
    }
}
