package com.example.stopwatch1;

import android.content.Context;

public class Chronometer implements Runnable {

    private Context mContext;
    private long mStartTime;
    private boolean mIsRunning;

    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    public Chronometer(Context mContext) {
        this.mContext = mContext;
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
        mIsRunning = true;
    }

    public void stop() {
        mIsRunning = false;
    }

    @Override
    public void run() {

        while (mIsRunning) {

            long since = 35000 - (System.currentTimeMillis() - mStartTime);
            int seconds = (int) ((since / 1000) % 60);
            int millis = (int) since % 100;

            ((MainActivity) mContext).updateTimerText(String.format(
                    "%02d.%02d", seconds, millis
            ));

            if (millis == 0 && seconds == 0) {

                mIsRunning = false;



            }


        }

    }
}
