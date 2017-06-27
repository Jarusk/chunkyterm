package com.jarusk.chunkyterm;

import com.google.api.client.util.Key;

/**
 * Wrapper for JSON object returned
 */
public class FitBitProfile {
    public static final String apiURL = "https://api.fitbit.com/1/user/-/profile.json";

    @Key("user")
    public FitBitUser user;
}