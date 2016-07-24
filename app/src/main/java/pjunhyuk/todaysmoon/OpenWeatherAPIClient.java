package pjunhyuk.todaysmoon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by YonseiSIT15_PJH_DT on 2016-07-21.
 */
public class OpenWeatherAPIClient {
    final static String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather";
    public Weather getWeather(int lat, int lon) {
        Weather w = new Weather();
        String urlString = openWeatherURL + "?lat=" + lat + "&lon=" + lon + "&APPID=2ee956233991ef8497dad31a0fb410e1";

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            w = parseJSON(json);
            w.setIon(lon);
            w.setLat(lat);
        }catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;
        }catch(JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;
        }catch(IOException e) {
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;
        }

        return w;
    }

    private Weather parseJSON(JSONObject json) throws JSONException {
        Weather w = new Weather();
        // Get main - temp
        w.setTemperature(json.getJSONObject("main").getInt("temp"));

        // Get city
        w.setCity(json.getString("name"));

        // Get weather - main
        w.setWeathermain(json.getJSONArray("weather").getJSONObject(0).getString("main"));

        return w;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
