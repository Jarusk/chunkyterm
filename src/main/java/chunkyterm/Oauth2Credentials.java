package chunkyterm;

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
 */
class Oauth2Credentials {

    private static final File configFile = new java.io.File(System.getProperty("user.home"),".chunkyterm.toml");

    static String API_KEY = "";
    static String API_SECRET = "";
    static final String DOMAIN = "127.0.0.1";
    static final int PORT = 8080;

    static void loadCredentials() {

        try {

            Toml toml = new Toml().read(configFile);
            API_KEY = toml.getString("API_KEY", "");
            API_SECRET = toml.getString("API_SECRET", "");

            if (API_SECRET.isEmpty() || API_KEY.isEmpty() ) {
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
        System.exit(1);
    }
}
