package com.example.ag6505.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class PhoneReceiver extends BroadcastReceiver {

    MainActivity a;

    public PhoneReceiver(MainActivity mainActivity) {
        a = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast received", Toast.LENGTH_SHORT).show();

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale;

        a.tv.setText(String.valueOf(batteryPct));
    }
}
