package com.jarusk.chunkyterm;

import com.moandjiezana.toml.Toml;

import java.io.File;


 /**
 * Created by matt on 24/06/17.
 *
 * Provides the loading and storage of FitBit API credentials.
 * Reads a file named .chunkyterm.toml in the user's home directory.
 *
 * Must have the following structure:
 *
 * API_KEY = "something"
 * API_SECRET = "something"
 * CALLBACK_URL = "something"
 */
class Oauth2Credentials {

    private static final File configFile = new java.io.File(System.getProperty("user.home"),".chunkyterm.toml");

    static String API_KEY = "";
    static String API_SECRET = "";
    static String CALLBACK_URL = "";

    static void loadCredentials() {

        try {

            Toml toml = new Toml().read(configFile);
            API_KEY = toml.getString("API_KEY", "");
            API_SECRET = toml.getString("API_SECRET", "");
            CALLBACK_URL = toml.getString("CALLBACK_URL", "");

            if (API_SECRET.isEmpty() || API_KEY.isEmpty() || CALLBACK_URL.isEmpty()) {
                printHelpAndDie();
            }
        }
        catch (RuntimeException e){
            printHelpAndDie();
        }
    }

    private static void printHelpAndDie() {
        System.out.print("***INVALID config file!***\nPlease place a .chunkyterm.toml file in your Home directory.\n");
        System.out.println("Must have the following form (FitBit API creds):\n");
        System.out.println("API_KEY = \"something\"");
        System.out.println("API_SECRET = \"something\"");
        System.out.println("CALLBACK_URL = \"something\"");
        System.exit(1);
    }
}
