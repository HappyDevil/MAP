package com.map.mapmaxv1.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.map.mapmaxv1.R;

/**
 * Created by User on 20.04.2017.
 */

public class FragmentMap extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_marker, container, false);
    }
}
