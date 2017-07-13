package com.jarusk.chunkyterm;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.util.Key;

import java.io.IOException;

/**
 * Wrapper for JSON object returned
 */
public class FitBitProfile {
    public static final String apiURL = "https://api.fitbit.com/1/user/-/profile.json";

    @Key("user")
    public FitBitUser user;


    public static FitBitProfile getFitBitProfile(HttpRequestFactory requestFactory) {
        GenericUrl urlObject = new GenericUrl(apiURL);
        FitBitProfile result = new FitBitProfile();

        try {
            HttpRequest request = requestFactory.buildGetRequest(urlObject);
            result = request.execute().parseAs(FitBitProfile.class);
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }
}