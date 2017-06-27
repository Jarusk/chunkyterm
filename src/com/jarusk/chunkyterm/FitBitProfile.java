package com.jarusk.chunkyterm;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.List;

/**
 * Created by matt on 25/06/17.
 */
public class FitBitProfile {
    public static String apiURL = "https://api.fitbit.com/1/user/-/profile.json";

    @Key("user")
    public FitBitUser user;
}