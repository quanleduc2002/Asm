package com.example.asm_ph10517;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebView extends AppCompatActivity {
android.webkit.WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=findViewById(R.id.webView);
        intent=getIntent();
        String link=intent.getStringExtra("Openlink");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}