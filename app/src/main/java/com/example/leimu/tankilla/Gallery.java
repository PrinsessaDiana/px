package com.example.leimu.tankilla;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Leimu on 30.3.2017.
 */

public class Gallery extends Fragment {

    GridView gv;
    ArrayList<File> list;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Gallery");

        list = imageReader(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/foods"));
        gv = (GridView) getView().findViewById(R.id.grid_view);
        gv.setAdapter(new GridAdapter());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.gallery, container, false);
    }

    private class GridAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getActivity().getLayoutInflater().inflate(R.layout.single_grid, viewGroup, false);
            ImageView iv = (ImageView) view.findViewById(R.id.imageView2);

            iv.setImageURI(Uri.parse( getItem(i).toString()));

            return view;
        }
    }

    ArrayList<File> imageReader(File root) {
        ArrayList<File> a = new ArrayList<>();

        File[] files = root.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                a.addAll(imageReader((files[i])));
            } else {

                if (files[i].getName().endsWith(".png")) {
                    a.add(files[i]);

                }


            }

        } return a;
    }
}


