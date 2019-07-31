package com.instagram_parser.Dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.instagram_parser.R;
import com.instagram_parser.Service.AuthenticationListener;
import com.instagram_parser.System.Constants;
import com.instagram_parser.Util.ToastUtil;

public class AuthenticationDialog extends Dialog {


        private final AuthenticationListener listener;
        private Context context;

        private WebView web_view;

        private String loginURL;

        public AuthenticationDialog(@NonNull Context context, AuthenticationListener listener) {
            super(context);
            this.context = context;
            this.listener = listener;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.auth_dialog);
            loginURL  = Constants.BASE_URL
                    + "oauth/authorize/?client_id="
                    + Constants.CLIENT_ID
                    + "&redirect_uri="
                    + Constants.CALLBACK_URL
                    + "&response_type=token"
                    + "&display=touch&scope=public_content";
            initializeWebView();
        }

        private void initializeWebView() {
            web_view = findViewById(R.id.web_view);
            web_view.getSettings().setDomStorageEnabled(true);
            web_view.getSettings().setJavaScriptEnabled(true);
            web_view.clearCache(true);

            web_view.setWebViewClient(new WebViewClient() {

                boolean authComplete = false;

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(loginURL);
                    return false;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }

                String access_token;

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                    if (url.contains("#access_token=") && !authComplete) {
                        Uri uri = Uri.parse(url);
                        access_token = uri.getEncodedFragment();
                        // get the whole token after the '=' sign
                        access_token = access_token.substring(access_token.lastIndexOf("=")+1);
                        Log.i("", "CODE : " + access_token);
                        authComplete = true;
                        listener.onCodeReceived(access_token);
                        dismiss();

                    } else if (url.contains("?error")) {
                        ToastUtil.show((Activity) context, R.string.instagramaBaglanHata, ToastUtil.TOAST_ERROR);
                        dismiss();
                    }
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        System.out.println(error.getDescription());
                    }
                }
            });
            web_view.loadUrl(loginURL);
        }

}