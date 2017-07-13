package chunkyterm;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.util.Key;

import java.io.IOException;
import java.util.List;

/**
 * Created by matt on 01/07/17.
 */
public class FitBitActivitiesHeart {
    static final String apiURL = "https://api.fitbit.com/1/user/-/activities/heart/date/2017-06-30/1d/1min.json";


    @Key("activities-heart")
    public List<AcitivitiesHeart> acitivitiesHeart;

    @Key
    public AcitivitiesHeartIntraday acitivitiesHeartIntraday;

    private class AcitivitiesHeartIntraday {
        @Key
        List<HeartReading> dataset;

        @Key
        Integer datasetInterval;

        @Key
        String datasetType;
    }

    private class HeartReading {
        @Key
        String time;

        @Key
        Integer value;
    }


    public static FitBitActivitiesHeart getFitBitActivitiesHeart(HttpRequestFactory requestFactory) {
        GenericUrl urlObject = new GenericUrl(apiURL);
        FitBitActivitiesHeart result = new FitBitActivitiesHeart();

        try {
            HttpRequest request = requestFactory.buildGetRequest(urlObject);
            result = request.execute().parseAs(FitBitActivitiesHeart.class);
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }
}
