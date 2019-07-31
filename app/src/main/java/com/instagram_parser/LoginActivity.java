package com.instagram_parser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.instagram_parser.Dialog.AuthenticationDialog;
import com.instagram_parser.Service.AuthenticationListener;
import com.instagram_parser.System.Constants;
import com.instagram_parser.Util.SharedUtil;

public class LoginActivity extends AppCompatActivity implements AuthenticationListener  {
        private AuthenticationDialog auth_dialog;
        private Button btn_get_access_token;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            btn_get_access_token = findViewById(R.id.login_connect);

            btn_get_access_token.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth_dialog = new AuthenticationDialog(LoginActivity.this, LoginActivity.this);
                    auth_dialog.setCancelable(true);
                    auth_dialog.show();
                }
            });
        }

        @Override
        public void onCodeReceived(String access_token) {
            if (access_token == null) {
                auth_dialog.dismiss();
            }
            SharedUtil.setDefaults(Constants.SHARED_ACCESS_TOKEN,access_token,getApplicationContext());

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

}