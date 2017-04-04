package com.example.leimu.tankilla;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.icu.util.Output;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.graphics.Bitmap.CompressFormat.PNG;
import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by Leimu on 30.3.2017.
 */

public class TakePicture extends Fragment {

    public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1777;


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button camButton = (Button) getView().findViewById(R.id.btnCamera);

        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        getActivity().setTitle("Take Picture!");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return inflater.inflate(R.layout.takepicture, container, false);
    }

    public void openCamera() {

       // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      //   startActivityForResult(intent, 0);

        Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
        //File file = FileProvider.getUriForFile(file, getContext().getApplicationContext().getPackageName() + ".provider", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");

       // FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", createImageFile());

        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent2, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        ImageView capturedCam = (ImageView) getView().findViewById(R.id.capturedImage);

        //Check that request code matches ours:
        if (requestCode == CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE)
        {
            //Get our saved file into a bitmap object:
            File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
            Bitmap bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1000, 700);
            capturedCam.setImageBitmap(bitmap);
            saveImage(bitmap);
        }
    }

    private Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

        // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);


    }

    private void saveImage(Bitmap finalBitmap) {

        File myDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/foods");
        System.out.println(myDir);

        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Imagea-"+ n +".png";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(PNG, 80, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}