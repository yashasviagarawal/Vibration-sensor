package com.example.viberationsensor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    Vibrator vibrator;

    EditText value;
    Button check;

    int i=0;
    int num = 0;
    int max = 10;
    int min = 1;
    int vibrations;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = findViewById(R.id.value);
        check = findViewById(R.id.check);

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        //generating random value each time app runs
        num = ThreadLocalRandom.current().nextInt(min,max);
        Toast.makeText(MainActivity.this,""+num,Toast.LENGTH_SHORT).show();

        //timer thread to run function automatically after launch of application
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                vibrate(num);
            }
        },1000);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibrations = Integer.parseInt(value.getText().toString());
                if(vibrations==num){
                    Toast.makeText(MainActivity.this,"Vibrations successful",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"vibrator didn't worked",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void vibrate(int num){
        while(i<num){
            try {
                vibrator.vibrate(200);
                i+=1;
                Thread.sleep(1000);//for giving a pause between each vibration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}