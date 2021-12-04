package com.example.asm_ph10517;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ChucNangActivityFacebook extends AppCompatActivity {
    EditText edtTitlte, edtDescription, edtUrl;
    Button btnShareLink, btnshareImg, btnclickvideo, btnshareVideo;
    ImageView imghinh;
    VideoView videoView;
    ShareDialog shareDialog;
    ShareLinkContent shareContent;
    public static int Select_Image = 1;
    public static int Pick_video=2;
    Bitmap bitmap;
    Uri selectVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_nang_facebook);
        edtTitlte = findViewById(R.id.edtTitlte);
        edtDescription = findViewById(R.id.edtDescription);
        edtUrl = findViewById(R.id.edtUrl);
        btnShareLink = findViewById(R.id.btnShareLink);
        btnshareImg = findViewById(R.id.btnshareImg);
        btnclickvideo = findViewById(R.id.btnclickvideo);
        btnshareVideo = findViewById(R.id.btnshareVideo);
        imghinh = findViewById(R.id.imgHinh);
        videoView = findViewById(R.id.ViewVideo);
        shareDialog = new ShareDialog(ChucNangActivityFacebook.this);
        btnshareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePhoto sharePhoto = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                shareDialog.show(sharePhotoContent);
            }
        });
        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse(edtTitlte.getText().toString()))
                            .setContentDescription(edtDescription.getText().toString())
                            .setContentUrl(Uri.parse(edtUrl.getText().toString()))
                            .build();
                }
                shareDialog.show(shareContent);
            }
        });
        imghinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Select_Image);
            }
        });
btnclickvideo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent,Pick_video);



    }
});
btnshareVideo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ShareVideo shareVideo=new  ShareVideo.Builder()
                .setLocalUrl(selectVideo)
                .build();
        ShareVideoContent shareVideoContent=new ShareVideoContent.Builder()
                .setVideo(shareVideo)
                .build();
        shareDialog.show(shareVideoContent);
        videoView.stopPlayback();
    }
});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Select_Image && resultCode == RESULT_OK) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                imghinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == Pick_video && resultCode == RESULT_OK){
            selectVideo=data.getData();
            videoView.setVideoURI(selectVideo);
            videoView.start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}