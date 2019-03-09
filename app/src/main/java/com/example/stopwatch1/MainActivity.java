package com.example.stopwatch1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTime;
    private EditText mEtLaps;
    private Button mBtnStart;
    private Button mBtnLap;
    private Button mBtnStop;
    private int mLaps = 1;
    private ScrollView mSvLaps;

    private Context mContext;

    private Chronometer mChronometer;
    private Thread mThreadChrono;

    private View.OnTouchListener button = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            System.out.println("hello");
            return true;
        }
    };


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mTvTime = findViewById(R.id.tv_time);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnLap = findViewById(R.id.btn_lap);
        mBtnStop = findViewById(R.id.btn_stop);
        mEtLaps = findViewById(R.id.et_laps);
        mSvLaps = findViewById(R.id.sv_mSvLaps);

        mEtLaps.setEnabled(false);


//
//        mBtnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (mChronometer == null) {
//                    mChronometer = new Chronometer(mContext);
//                    mThreadChrono = new Thread(mChronometer);
//                    mThreadChrono.start();
//                    mChronometer.start();
//                    mBtnStart.setBackgroundColor(Color.parseColor("#900010"));
//                    mBtnStart.setText("POINT");
//
//                }
//
//                if (mChronometer != null) {
//                    mEtLaps.append("Point: " + mLaps + " " + String.valueOf(mTvTime.getText()) + "\n");
//                    mSvLaps.smoothScrollTo(0, mEtLaps.getBottom());
//                    mLaps++;
//                }
//
//
//            }
//        });

        //
        mBtnStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    if (mChronometer == null) {
                        mChronometer = new Chronometer(mContext);
                        mThreadChrono = new Thread(mChronometer);
                        mThreadChrono.start();
                        mChronometer.start();
                        mBtnStart.setBackgroundColor(Color.parseColor("#900010"));
                        mBtnStart.setText("POINT");

                    }
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mChronometer != null) {
                        mEtLaps.append("Point: " + mLaps + " " + String.valueOf(mTvTime.getText()) + "\n");
                        mSvLaps.smoothScrollTo(0, mEtLaps.getBottom());
                        mLaps++;
                    }
                    return true;
                }

                return false;
            }
        });


        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChronometer != null) {
                    mChronometer.stop();
                    mThreadChrono.interrupt();
                    mThreadChrono = null;
                    mChronometer = null;
                    mEtLaps.setText("");
                    mLaps = 1;
                }
            }
        });

    }

    public void updateTimerText(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mTvTime.setText(time);

            }
        });

    }
}
