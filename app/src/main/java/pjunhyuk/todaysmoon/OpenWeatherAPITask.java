package pjunhyuk.todaysmoon;

import android.os.AsyncTask;

/**
 * Created by YonseiSIT15_PJH_DT on 2016-07-21.
 */
public class OpenWeatherAPITask extends AsyncTask<Integer, Void, Weather> {
    @Override
    public Weather doInBackground(Integer...params) {
        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        int lat = params[0];
        int lon = params[1];
        Weather w = client.getWeather(lat, lon);

        return w;
    }
}