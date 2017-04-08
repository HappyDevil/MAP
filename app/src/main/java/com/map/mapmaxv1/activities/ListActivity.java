package com.map.mapmaxv1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.map.mapmaxv1.R;
import com.map.mapmaxv1.ResizeAnimation;
import com.map.mapmaxv1.dto.MarkDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.03.2017.
 */

public class ListActivity extends AppCompatActivity {

    private RecyclerViewAdapterList adapterList;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<MarkDTO> markDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewList);

        markDTO = getIntent().getParcelableArrayListExtra("MarkList");

        layoutManager = new LinearLayoutManager(this);

        adapterList = new RecyclerViewAdapterList(markDTO);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterList);

    }



    public void onClickCard(View view) {
        int targetSize = view.getHeight()+200;

        ResizeAnimation resizeAnimation = new ResizeAnimation(view, targetSize);
        resizeAnimation.setDuration(600);
        view.startAnimation(resizeAnimation);
    }

    public void onClickButton(View view){
        Intent intent = new Intent(ListActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
