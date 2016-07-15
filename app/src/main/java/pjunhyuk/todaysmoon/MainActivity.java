package pjunhyuk.todaysmoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton imgbutton;
    ImageView cloud_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For SplashActivity
        startActivity(new Intent(this, SplashActivity.class));

        // For Setting
        imgbutton = (ImageButton)findViewById(R.id.button_setting);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        // For cloud patrol
        cloud_1 = (ImageView) findViewById(R.id.cloud_1);
        final Animation animPatrol = AnimationUtils.loadAnimation(this, R.anim.translate);
        cloud_1.startAnimation(animPatrol);
    }
}