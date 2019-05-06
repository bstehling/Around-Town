package com.example.aroundtown;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NotificationCompat;
import android.widget.TimePicker;

import java.util.Calendar;

public class NotificationView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
    }

    final HttpUtils httpUtils = new HttpUtils();

    protected static void addNotification(Context context, Class<?> cls, int hour, int minute, int date) {
        Calendar calendar = Calendar.getInstance();
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.set(Calendar.HOUR_OF_DAY, hour);
        setCalendar.set(Calendar.MINUTE, minute);
        setCalendar.set(Calendar.SECOND, 00);
        setCalendar.set(Calendar.DATE, date);

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        Intent activate = new Intent(context, Alarm.class);
        AlarmManager alarms;
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, activate, 0);
    }
}