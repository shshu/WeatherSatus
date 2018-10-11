/**
 * Created by john on 10/10/18.
 */
public class MainData {
    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    // TODO: function for Celsius and Fahrenheit
    public double getTemp() {
        return this.temp;
    }

    public double getTemp_max() {
        return this.temp_max;
    }

    public double getTemp_min() {
        return this.temp_min;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public double getPressure() {
        return this.pressure;
    }
}
