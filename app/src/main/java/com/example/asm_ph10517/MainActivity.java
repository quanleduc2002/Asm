package com.example.asm_ph10517;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
ImageView imgKhoaHoc,imgMap,imgnew,imgSocial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("HỖ TRỢ HỌC TẬP");
        overridePendingTransition(R.anim.toleft,R.anim.toright);
        imgKhoaHoc=findViewById(R.id.imgKhoaHoc);
        imgMap=findViewById(R.id.imgMap);
        imgnew=findViewById(R.id.imgnew);
        imgSocial=findViewById(R.id.imgSocial);
        imgnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TinTucActivity.class);
                startActivity(intent);
            }
        });
        imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        imgKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,KhoaHocActivity.class);
                startActivity(intent);
            }
        });
        imgSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Social.class);
                startActivity(intent);
            }
        });
    }
}