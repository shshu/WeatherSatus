import java.util.Date;

/**
 * Created by john on 10/10/18.
 */
public class Sys {
    public long sunrise;
    public long sunset;
    public String country;

    public long getSunrise() {
        return this.sunrise;
    }

    public long getSunset() {
        return this.sunset;
    }

    public String getCountry() {
        return this.country;
    }

    public Date getSunriseDate() {
        return new  java.util.Date(this.sunrise*1000);
    }

    public Date getSunsetDate() {
        return new  java.util.Date(this.sunset*1000);
    }

    public long getSunlightTime() {
        return this.sunset-this.sunrise;
    }
}
