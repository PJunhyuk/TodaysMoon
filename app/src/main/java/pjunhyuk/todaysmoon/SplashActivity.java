package pjunhyuk.todaysmoon;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by YonseiSIT15_PJH_DT on 2016-07-08.
 */
public class SplashActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                finish();
            }
        };
        // TODO Auto-generated method stub
        // splash for 3.000 sec
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
