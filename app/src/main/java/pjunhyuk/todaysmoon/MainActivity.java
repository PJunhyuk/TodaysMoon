package pjunhyuk.todaysmoon;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// For GPS Permission - use TedPermission
// Reference : http://gun0912.tistory.com/55#_
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

// For live weather information on location - using openweathermap api
// Reference : http://bcho.tistory.com/1050

// For LunarToSolarTest
// Reference : http://bugnote.tistory.com/31

// For live time
// Reference : http://blog.opid.kr/247

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {
    // For GPS Permission - Make PermissionListener
    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    // For GPSInfo
    private TextView txtLat;
    private TextView txtLon;
    private GpsInfo gps;

    // Buttons
    ImageButton imgbutton;
    ImageView imgmoon;

    // For Live Weather
    Button getWeatherBtn;
    TextView tem;
    TextView weather_main;
    TextView textcity;

    // For live time
    TextView textlivetime;

    // For description
    TextView textview_description_1;
    TextView textview_description_2;
    TextView textview_description_3;

    // For other images
    ImageView star_8_1_1;
    ImageView star_9_1_1;
    ImageView star_9_1_2;
    ImageView star_9_1_3;
    ImageView cloud_9_2_1;
    ImageView cloud_9_2_2;
    ImageView cloud_9_2_3;

    String weathermain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For GPS Permission - Start TedPermission
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

        // For SplashActivity
        startActivity(new Intent(this, SplashActivity.class));

        // For Live weather
        getWeatherBtn = (Button)findViewById(R.id.getWeatherBtn);
        tem = (TextView)findViewById(R.id.tem);
        weather_main = (TextView)findViewById(R.id.weather_main);
        textcity = (TextView)findViewById(R.id.city);

        getWeatherBtn.setOnClickListener(mClickListener);

        // same as mClickListener - for auto run : start
        // GPS 정보를 보여주기 위한 이벤트 클래스 등록
        gps = new GpsInfo(MainActivity.this);
        // GPS 사용여부 가져오기
        if(gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // 날씨를 읽어오는 API 호출
            OpenWeatherAPITask t = new OpenWeatherAPITask();
            try {
                Weather w = t.execute(latitude,longitude).get();

                // temperature
                String temperature = String.valueOf( Math.round(w.getTemperature()-273.15) );
                tem.setText(temperature);

                // weather
                weathermain = String.valueOf(w.getWeathermain());
                weather_main.setText(weathermain);

                // city name
                String city = String.valueOf(w.getCity());
                textcity.setText(city);

            } catch(InterruptedException e) {
                e.printStackTrace();
            } catch(ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            // GPS를 사용할 수 없으므로
            gps.showSettingsAlert();
        }
        // same as mClickListener - for auto run : end

        // For live time
        textlivetime = (TextView)findViewById(R.id.livetime);
        long now = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", java.util.Locale.getDefault());
        Date date = new Date(now);
        String strdate = dateFormat.format(date);
        String moondate = LunarToSolarTest.convertSolarToLunar(strdate);

        // For other images
        star_8_1_1 = (ImageView)findViewById(R.id.star_8_1_1);
        star_9_1_1 = (ImageView)findViewById(R.id.star_9_1_1);
        star_9_1_2 = (ImageView)findViewById(R.id.star_9_1_2);
        star_9_1_3 = (ImageView)findViewById(R.id.star_9_1_3);
        cloud_9_2_1 = (ImageView)findViewById(R.id.cloud_9_2_1);
        cloud_9_2_2 = (ImageView)findViewById(R.id.cloud_9_2_2);
        cloud_9_2_3 = (ImageView)findViewById(R.id.cloud_9_2_3);

        star_8_1_1.setVisibility(star_8_1_1.GONE);
        star_9_1_1.setVisibility(star_9_1_1.GONE);
        star_9_1_2.setVisibility(star_9_1_2.GONE);
        star_9_1_3.setVisibility(star_9_1_3.GONE);
        cloud_9_2_1.setVisibility(cloud_9_2_1.GONE);
        cloud_9_2_2.setVisibility(cloud_9_2_2.GONE);
        cloud_9_2_3.setVisibility(cloud_9_2_3.GONE);

        // For Moon & description_2
        imgmoon = (ImageView)findViewById(R.id.moon);
        imgmoon.setBackgroundResource(R.drawable.moon_1_0);
        textview_description_2 = (TextView)findViewById(R.id.description_2);
        textview_description_2.setText("initialization");
        int Lunarday = Integer.parseInt(moondate.substring(8, 10));
        if(Lunarday == 1) {
            imgmoon.setBackgroundResource(R.drawable.moon_1_0);
            textview_description_2.setText("초승달이");
         }else if(Lunarday >=2 && Lunarday <= 6) {
            imgmoon.setBackgroundResource(R.drawable.moon_2_0);
            textview_description_2.setText("초승달이");
        }else if(Lunarday >=7 && Lunarday <= 10) {
            imgmoon.setBackgroundResource(R.drawable.moon_3_0);
            textview_description_2.setText("상현달이");
        }else if(Lunarday >= 11 && Lunarday <= 14) {
            imgmoon.setBackgroundResource(R.drawable.moon_4_0);
            textview_description_2.setText("상현달이");
        }else if(Lunarday == 15) {
            imgmoon.setBackgroundResource(R.drawable.moon_5_0);
            textview_description_2.setText("보름달이");
        }else if(Lunarday >= 16 && Lunarday <= 19) {
            imgmoon.setBackgroundResource(R.drawable.moon_6_0);
            textview_description_2.setText("하현달이");
        }else if(Lunarday >= 20 && Lunarday <= 23) {
            imgmoon.setBackgroundResource(R.drawable.moon_7_0);
            textview_description_2.setText("하현달이");
        }else if(Lunarday >= 24 && Lunarday <= 28) {
            imgmoon.setBackgroundResource(R.drawable.moon_8_0);
            textview_description_2.setText("그믐달이");
            if(weathermain.equals("Haze")) {

            } else {
                star_8_1_1.setVisibility(star_8_1_1.VISIBLE);
            }
        }else {
            imgmoon.setBackgroundResource(R.drawable.moon_9_0);
            textview_description_2.setText("그믐달이");
            if(weathermain.equals("Haze") || weathermain.equals("Clouds")) {
                cloud_9_2_1.setVisibility(cloud_9_2_1.VISIBLE);
                cloud_9_2_2.setVisibility(cloud_9_2_2.VISIBLE);
                cloud_9_2_3.setVisibility(cloud_9_2_3.VISIBLE);
            } else {
                star_9_1_1.setVisibility(star_9_1_1.VISIBLE);
                star_9_1_2.setVisibility(star_9_1_2.VISIBLE);
                star_9_1_3.setVisibility(star_9_1_3.VISIBLE);
            }
        }
        textlivetime.setText(Integer.toString(Lunarday));

        // For description_1
        textview_description_1 = (TextView)findViewById(R.id.description_1);
        textview_description_1.setText("오늘은 누구보다 밝은");

        // For description_3
        textview_description_3 = (TextView)findViewById(R.id.description_3);
        textview_description_3.setText("그대의 남동쪽 하늘에 떠있습니다.");

        // For Setting
        imgbutton = (ImageButton) findViewById(R.id.button_setting);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

//        // For cloud patrol
//        cloud_1 = (ImageView) findViewById(R.id.cloud_1);
//        final Animation animPatrol1 = AnimationUtils.loadAnimation(this, R.anim.translate_1);
//        cloud_1.startAnimation(animPatrol1);
//        cloud_2 = (ImageView) findViewById(R.id.cloud_2);
//        final Animation animPatrol2 = AnimationUtils.loadAnimation(this, R.anim.translate_2);
//        cloud_2.startAnimation(animPatrol2);
//        cloud_3 = (ImageView) findViewById(R.id.cloud_3);
//        final Animation animPatrol3 = AnimationUtils.loadAnimation(this, R.anim.translate_3);
//        cloud_3.startAnimation(animPatrol3);
//        cloud_4 = (ImageView) findViewById(R.id.cloud_4);
//        final Animation animPatrol4 = AnimationUtils.loadAnimation(this, R.anim.translate_3);
//        cloud_4.startAnimation(animPatrol4);
//
//        // For star twinkle
//        star_1 = (ImageView) findViewById(R.id.star_1);
//        final Animation animTwinkle1 = AnimationUtils.loadAnimation(this, R.anim.twinkle_1);
//        star_1.startAnimation(animTwinkle1);
//        star_2 = (ImageView) findViewById(R.id.star_2);
//        final Animation animTwinkle2 = AnimationUtils.loadAnimation(this, R.anim.twinkle_1);
//        star_2.startAnimation(animTwinkle2);
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            // For live time update
            long now = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
            Date date = new Date(now);
            textlivetime.setText(dateFormat.format(date));

            // GPS 정보를 보여주기 위한 이벤트 클래스 등록
            gps = new GpsInfo(MainActivity.this);
            // GPS 사용여부 가져오기
            if(gps.isGetLocation()) {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                txtLat.setText(String.valueOf(latitude));
                txtLon.setText(String.valueOf(longitude));

                // 날씨를 읽어오는 API 호출
                OpenWeatherAPITask t = new OpenWeatherAPITask();
                try {
                    Weather w = t.execute(latitude,longitude).get();

                    // temperature
                    String temperature = String.valueOf( Math.round(w.getTemperature()-273.15) );
                    tem.setText(temperature);

                    // weather
                    String weathermain = String.valueOf(w.getWeathermain());
                    weather_main.setText(weathermain);

                    // city name
                    String city = String.valueOf(w.getCity());
                    textcity.setText(city);

                } catch(InterruptedException e) {
                    e.printStackTrace();
                } catch(ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                // GPS를 사용할 수 없으므로
                gps.showSettingsAlert();
            }
        }
    };
}