import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by john on 10/10/18.
 */
public class WeatherStatus {
    static final String APPID = "APP_ID_KEY";
    static final String defaultCity = "Tel Aviv";
    static final String CITYWEATHERFROMAT = "City: %s\nWeather data: %s\nSunrise: %d Sunset: %d\nTemp: %f\n";

    private static final Logger LOGGER = Logger.getLogger(WeatherStatus.class.getName());

    public static void main(String[] args) {
        Settings settings = new Settings();
        JCommander jcommander = null;
        String path = null;
        String appid = APPID;
        List<String> cities = new ArrayList<String>();

        try {
            jcommander = new JCommander(settings, args);
        } catch (ParameterException e) {
            LOGGER.warning("Logger Name: "+LOGGER.getName() + e.getMessage());
            return;
        }

        // Help has been selected
        if (settings.getHelp()) {
            jcommander.usage();
            return;
        }

        // Configuration file has been selected
        if (settings.getPath() != null) {
            path = settings.getPath();
            List <String> fileCities = Utility.getLinesFromFile(path);
            if (null != fileCities) {
                for (String c : fileCities) {
                    cities.add(c);
                }
            }
        }

        if (settings.getCity() != null) {cities.add(settings.getCity());}

        // No valid configuration file use default
        if (cities.size() == 0) { cities.add(defaultCity);}

        // Authentication token has been selected
        if (settings.getAuthenticationToken() != null) { appid = settings.getAuthenticationToken();}

        List<Weather> citiesWeather = getCitiesWeather(cities);
        if (citiesWeather.size() == 0) {
            LOGGER.info("Logger Name: "+LOGGER.getName() + "Cloud not obtain city weather data");
            return;
        }

        Weather shortestDay = null;
        long minDayTime = Long.MAX_VALUE;

        Weather longestDay = null;
        long maxDayTime = Long.MIN_VALUE;

        for (Weather cw : citiesWeather) {
            prettyPrint(cw);

            if (maxDayTime < cw.getSys().getSunlightTime()) {
                maxDayTime = cw.getSys().getSunlightTime();
                longestDay = cw;
            }

            if (cw.getSys().getSunlightTime() < minDayTime) {
                minDayTime = cw.getSys().getSunlightTime();
                shortestDay = cw;
            }
        }

        if (null != shortestDay) {
            System.out.println("Shortest day time city: " + shortestDay.getName() +
                    " MinTemp: "+ shortestDay.getMain().getTemp_min() + " MaxTemp: "+shortestDay.getMain().getTemp_max());
        }
        if (null != longestDay) {
            System.out.println("Longest day time city: " + longestDay.getName() +
                    " MinTemp: "+ longestDay.getMain().getTemp_min() + " MaxTemp: "+longestDay.getMain().getTemp_max());
        }


    }

    // This function should be refactor
    private static void prettyPrint(Weather cw) {
        System.out.println("_____________________");
        String cityName = cw.getName();
        String weatherData = "";
        for (WeatherCur wd : cw.getWeather()) {
            weatherData += wd.getDescription()+" ";
        }
        long sunrise = cw.getSys().getSunrise();
        long sunset = cw.getSys().getSunset();
        double temp = cw.getMain().getTemp();
        String cityWeather = String.format(CITYWEATHERFROMAT,cityName, weatherData, sunrise,sunset,temp);
        System.out.print(cityWeather);
        System.out.println("_____________________");
    }

    private static List<Weather> getCitiesWeather(List<String> cities) {
        List<Weather> citiesWeather = new ArrayList<Weather>();
        for (String city : cities) {
            String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s",city ,"2129b0d4a034345575dc2dca4e05a2e6");
            String res = Utility.getUrl(url);
            if (null == res) {
                LOGGER.info("Logger Name: "+LOGGER.getName() + "Failed to get url");
                continue;
            }

            Weather w = (Weather) Utility.jsonConvert(res, Weather.class);
            if (null == w) {
                LOGGER.info("Logger Name: "+LOGGER.getName() + "Failed to convert json response");
                continue;
            }

            citiesWeather.add(w);
        }
        return citiesWeather;
    }
}