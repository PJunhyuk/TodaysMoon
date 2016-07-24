package pjunhyuk.todaysmoon;

/**
 * Created by YonseiSIT15_PJH_DT on 2016-07-21.
 */
public class Weather {
    double lat;
    double lon;
    int temperature;
    int cloudy;
    String weathermain;
    String city;

    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setIon(double lon) {
        this.lon = lon;
    }
    public void setTemperature(int t) {
        this.temperature = t;
    }
    public void setCloudy(int cloudy) {
        this.cloudy = cloudy;
    }
    public void setWeathermain(String wm) { this.weathermain = wm; }
    public void setCity(String city) {
        this.city = city;
    }

    public double getLat() {
        return lat;
    }
    public double getIon() {
        return lon;
    }
    public int getTemperature() {
        return temperature;
    }
    public int getCloudy() {
        return cloudy;
    }
    public String getWeathermain() { return weathermain; }
    public String getCity() {
        return city;
    }

}
