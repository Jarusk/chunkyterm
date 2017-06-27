package com.jarusk.chunkyterm;

import com.google.api.client.util.Key;

/**
 * Wrapper for JSON object returned
 */
class FitBitProfile {
    static String apiURL = "https://api.fitbit.com/1/user/-/profile.json";

    @Key("user")
    FitBitUser user;
}