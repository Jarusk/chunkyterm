package chunkyterm;

import com.google.api.client.util.Key;

import java.util.List;

/**
 * Created by matt on 01/07/17.
 */
public class AcitivitiesHeart {
    @Key
    public String dateTime;

    @Key
    public List<HeartRateZone> heartRateZones;

    @Key("value")
    public String value;
}
