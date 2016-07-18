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

import java.util.ArrayList;

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
    private Button btnShowLocation;
    private TextView txtLat;
    private TextView txtLon;
    private GpsInfo gps;

    // Buttons
    ImageButton imgbutton;
    ImageView cloud_1;
    ImageView cloud_2;
    ImageView cloud_3;
    ImageView cloud_4;
    ImageView star_1;
    ImageView star_2;
    ImageView star_3;
    ImageView star_4;
    ImageView star_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For GPS Permission - Start TedPermission
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

        // For SplashActivity
        startActivity(new Intent(this, SplashActivity.class));

        // For Setting
        imgbutton = (ImageButton) findViewById(R.id.button_setting);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        // For cloud patrol
        cloud_1 = (ImageView) findViewById(R.id.cloud_1);
        final Animation animPatrol1 = AnimationUtils.loadAnimation(this, R.anim.translate_1);
        cloud_1.startAnimation(animPatrol1);
        cloud_2 = (ImageView) findViewById(R.id.cloud_2);
        final Animation animPatrol2 = AnimationUtils.loadAnimation(this, R.anim.translate_2);
        cloud_2.startAnimation(animPatrol2);
        cloud_3 = (ImageView) findViewById(R.id.cloud_3);
        final Animation animPatrol3 = AnimationUtils.loadAnimation(this, R.anim.translate_3);
        cloud_3.startAnimation(animPatrol3);
        cloud_4 = (ImageView) findViewById(R.id.cloud_4);
        final Animation animPatrol4 = AnimationUtils.loadAnimation(this, R.anim.translate_3);
        cloud_4.startAnimation(animPatrol4);

        // For star twinkle
        star_1 = (ImageView) findViewById(R.id.star_1);
        final Animation animTwinkle1 = AnimationUtils.loadAnimation(this, R.anim.twinkle_1);
        star_1.startAnimation(animTwinkle1);
        star_2 = (ImageView) findViewById(R.id.star_2);
        final Animation animTwinkle2 = AnimationUtils.loadAnimation(this, R.anim.twinkle_1);
        star_2.startAnimation(animTwinkle2);

        // Start for GPSInfo
        btnShowLocation = (Button)findViewById(R.id.btn_start);
        txtLat = (TextView)findViewById(R.id.Latitude);
        txtLon = (TextView)findViewById(R.id.Longitude);

        // GPS 정보를 보여주기 위한 이벤트 클래스 등록
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                gps = new GpsInfo(MainActivity.this);

                // GPS 사용여부 가져오기
                if(gps.isGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    txtLat.setText(String.valueOf(latitude));
                    txtLon.setText(String.valueOf(longitude));
                    Toast.makeText(getApplicationContext(), "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    // GPS를 사용할 수 없으므로
                    gps.showSettingsAlert();
                }
            }
        });
        // End for GPSInfo
    }
}