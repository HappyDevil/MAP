package com.map.mapmaxv1.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.map.mapmaxv1.CircularTransformation;
import com.map.mapmaxv1.MainActivity;
import com.map.mapmaxv1.R;
import com.map.mapmaxv1.ResizeAnimation;
import com.map.mapmaxv1.dto.MarkDTO;
import com.squareup.picasso.Picasso;

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
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        imageView = (ImageView)findViewById(R.id.imageView3);
//        Picasso.with(this)
//                .load(R.drawable.z_9dc940eb)
//                .transform(new CircularTransformation(0))
//                .into(imageView);


        recyclerView = (RecyclerView) findViewById(R.id.recycleViewList);

        markDTO = getIntent().getParcelableArrayListExtra("MarkList");

        layoutManager = new LinearLayoutManager(this);

        adapterList = new RecyclerViewAdapterList(markDTO, this);


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

    public static Bitmap getCircleImageUsingShader(Bitmap sourse, int radius){
        if (sourse == null){ return null; }

        int diam = radius << 1;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        Bitmap scaleBitmap = Bitmap.createScaledBitmap(sourse, diam, diam, true);
        final Shader shader = new BitmapShader(scaleBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        Bitmap targetBitmap = Bitmap.createBitmap(diam, diam, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);

        canvas.drawCircle(radius, radius, radius, paint);

        return targetBitmap;
    }

}
