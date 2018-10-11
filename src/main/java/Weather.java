import java.util.List;

/**
 * Created by john on 10/10/18.
 */
public class Weather {
    private String name;
    private Sys sys;
    private MainData main;
    private int visibility;
    private int id;
    private long dt;
    private List<WeatherCur> weather;

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Sys getSys() {
        return this.sys;
    }

    public MainData getMain() {
        return this.main;
    }

    public long getDt() {
        return this.dt;
    }

    public List<WeatherCur> getWeather() {
        return weather;
    }
}
