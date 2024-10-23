package com.example.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MenuDiction extends Activity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diction);

        web = findViewById(R.id.webView);
        web.getSettings().setJavaScriptEnabled(true);

        web.loadUrl("https://dict.naver.com");
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
    }

    public class DictWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("Check URL", url);
            view.loadUrl(url);
            return true;
        }
    }

}
