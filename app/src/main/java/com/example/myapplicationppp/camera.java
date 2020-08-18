package com.example.myapplicationppp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

//import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class camera extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;
    Button b;
    BitmapDrawable drawel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        iv=findViewById(R.id.imageView);
        b=findViewById(R.id.button5);
        b.setOnClickListener(this);
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        startActivityForResult(intent,1000);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000&&resultCode==RESULT_OK){
            Bundle b=data.getExtras();
            Bitmap bmp=(Bitmap)b.get("data");

            ByteArrayOutputStream b2=new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,100,b2);
            byte b1[]= b2.toByteArray();


            String img1=android.util.Base64.encodeToString(b1,Base64.DEFAULT);
            Python python = Python.getInstance();
            PyObject pythonFile = python.getModule("a");
            PyObject b3=pythonFile.callAttr("aa",img1);
            String se=b3.toString();
            byte data1[] =android.util.Base64.decode(se,Base64.DEFAULT);

            Bitmap b5=BitmapFactory.decodeByteArray(data1,0,data1.length);


            iv.setImageBitmap(b5);
        }
    }

}
