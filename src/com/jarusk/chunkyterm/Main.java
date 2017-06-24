package com.jarusk.chunkyterm;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Main {

    public static final String version = "0.1";

    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),".store/chunkyterm");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final String[] FITBIT_SCOPE = {"sleep","settings","nutrition","activity","social","heartrate","profile","weight","location"};
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String TOKEN_SERVER_URL = "https://api.fitbit.com/oauth2/token";
    private static final String AUTHORIZATION_SERVER_URL = "https://www.fitbit.com/oauth2/authorize";


    /**
     * This will authorize the Java application against the Oauth2 API
     * @return Returns our credentials for Oauth2, whether new or from storage
     * @throws Exception Occurs when there is an error in communicating with the FitBit API
     */
    private static Credential authorize() throws Exception {
        //Load in our credentials file
        Oauth2Credentials.loadCredentials();

        AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl(TOKEN_SERVER_URL),
                new ClientParametersAuthentication(
                        Oauth2Credentials.API_KEY,
                        Oauth2Credentials.API_SECRET),
                Oauth2Credentials.API_KEY,
                AUTHORIZATION_SERVER_URL)
                .setScopes(Arrays.asList(FITBIT_SCOPE))
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setRequestInitializer(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest httpRequest) throws IOException {
                        String authString = "Basic "+ Base64.encodeBase64String(String.format("%s:%s", new Object[]{Oauth2Credentials.API_KEY,Oauth2Credentials.API_SECRET}).getBytes(Charset.defaultCharset()));
                        httpRequest.getHeaders().setAuthorization(authString);
                    }
                })
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setHost(Oauth2Credentials.DOMAIN)
                .setPort(Oauth2Credentials.PORT)
                .build();

        return new AuthorizationCodeInstalledApp(flow,receiver).authorize("user");
    }

    private static void run(HttpRequestFactory requestFactory) throws IOException {

    }


    /**
     * Generate our authentication token, whether from storage or the interwebs.
     *
     * We return a request factory that will include the needed headers when performing a request
     *
     * @return A request factory to talk to the FitBit API
     */
    private static HttpRequestFactory buildHttpRequestFactory() {
        HttpRequestFactory requestFactory = null;

        // This chunk will fetch/build our needed credentials for talking to the API
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final Credential credential = authorize();

            requestFactory =
                    HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest httpRequest) throws IOException {
                            credential.initialize(httpRequest);
                            httpRequest.setParser(new JsonObjectParser(JSON_FACTORY));
                        }
                    });

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return requestFactory;
    }

    private static void printHelp() {
        String out = "ChunkyTerm v"+version+"\n";
        out += "\nUsage: chunky [options]\n\n";
        out += "\nheart";


        System.out.println(out);
    }

    public static void main(String[] args) {

        if (args.length < 1){
            //Print out our help info
            printHelp();
            System.exit(0);
        }

        HttpRequestFactory factory = buildHttpRequestFactory();

    }
}