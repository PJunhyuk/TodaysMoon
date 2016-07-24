package pjunhyuk.todaysmoon;

import android.os.AsyncTask;

/**
 * Created by YonseiSIT15_PJH_DT on 2016-07-21.
 */
public class OpenWeatherAPITask extends AsyncTask<Double, Void, Weather> {
    @Override
    public Weather doInBackground(Double...params) {
        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        double lat = params[0];
        double lon = params[1];
        // API 호출
        Weather w = client.getWeather(lat, lon);

        return w;
    }
}