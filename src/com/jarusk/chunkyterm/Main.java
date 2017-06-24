package com.jarusk.chunkyterm;

public class Main {

    private static String appID = "";

    public static void main(String[] args) {
        Oauth2Credentials.loadCredentials();
        String KEY = Oauth2Credentials.API_KEY;
        String SECRET = Oauth2Credentials.API_SECRET;
        String CALLBACK_URL = Oauth2Credentials.CALLBACK_URL;
    }
}
