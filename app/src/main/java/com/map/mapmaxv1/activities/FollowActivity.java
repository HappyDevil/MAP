package com.map.mapmaxv1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.dto.FollowDTO;
import com.map.mapmaxv1.dto.UserDTO;

import java.util.List;

public class FollowActivity extends AppCompatActivity {

    private RecyclerView mRecycleView1;
    private RecyclerViewAdapterFollow mAdapter1;
    private StaggeredGridLayoutManager mGridLayoutManager1;
    private List<FollowDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /**
         * ЗДЕСЬ ЗАГРУЗКА СПИСКОВ ИЗ БАЗЫ ДАННЫХ
         */

//        FollowDTO b = new FollowDTO();
//        b.setFIO("Max Crazy");
//        b.setRole("Photographer");
//        list.add(b);

        mRecycleView1 = (RecyclerView)findViewById(R.id.rv1);

        mGridLayoutManager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView1.setItemAnimator(new DefaultItemAnimator());
        mRecycleView1.setLayoutManager(mGridLayoutManager1);
        mAdapter1 = new RecyclerViewAdapterFollow(list);
        mRecycleView1.setAdapter(mAdapter1);
    }
}
