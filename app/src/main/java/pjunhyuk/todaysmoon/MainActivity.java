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
    }
}