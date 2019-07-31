package com.instagram_parser.Service;

public interface AuthenticationListener {

    void onCodeReceived(String auth_token);

}