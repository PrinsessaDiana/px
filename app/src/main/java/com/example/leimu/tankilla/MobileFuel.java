package com.example.leimu.tankilla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Leimu on 26.3.2017.
 */

public class MobileFuel extends Fragment {


public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getActivity().setTitle("Mobiilitankkaus");
}


@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.mobiilitankkaus, container, false);
}

}