package com.xfc.lovebank.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import com.xfc.lovebank.R;
import com.xfc.lovebank.ui.base.BBBaseView;
import com.xfc.lovebank.utils.BBAppResource;
import com.xfc.lovebank.utils.CONST_VALUES;

import java.util.Calendar;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 4:56 PM
 */
public class BBUtilsView extends BBBaseView {
    Button wakeup;
    Button fallAsleep;
    Button legLift;
    public BBUtilsView(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View rootView = getInflater().inflate(R.layout.fragment_utils, this, false);
        wakeup = (Button)rootView.findViewById(R.id.bt_wake_up);
        fallAsleep = (Button)rootView.findViewById(R.id.bt_fall_asleep);
        legLift = (Button)rootView.findViewById(R.id.bt_leg_lift);

        wakeup.setOnClickListener(wakeupClick);
        fallAsleep.setOnClickListener(fallAsleepClick);
        legLift.setOnClickListener(legLiftClick);
        return rootView;
    }

    private OnClickListener wakeupClick = new OnClickListener() {
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        @Override
        public void onClick(View v) {
            Calendar beginTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            endTime.add(Calendar.MINUTE, 5);

            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, BBAppResource.string(R.string.wake_up_title, CONST_VALUES.title_identifier))
                    .putExtra(CalendarContract.Events.DESCRIPTION, BBAppResource.string(R.string.wake_up_description))
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                    .putExtra(CalendarContract.Events.ALLOWED_REMINDERS, CalendarContract.Reminders.METHOD_DEFAULT)
                    ;
            getContext().startActivity(intent);
        }
    };

    private OnClickListener fallAsleepClick = new OnClickListener() {
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        @Override
        public void onClick(View v) {
            Calendar beginTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            endTime.add(Calendar.MINUTE, 30);
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, BBAppResource.string(R.string.sleep_title, CONST_VALUES.title_identifier))
                    .putExtra(CalendarContract.Events.DESCRIPTION, BBAppResource.string(R.string.sleep_description))
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            getContext().startActivity(intent);
        }
    };

    private OnClickListener legLiftClick = new OnClickListener() {
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        @Override
        public void onClick(View v) {
            Calendar beginTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            endTime.add(Calendar.MINUTE, 5);
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, BBAppResource.string(R.string.leg_lift_title, CONST_VALUES.title_identifier))
                    .putExtra(CalendarContract.Events.DESCRIPTION, BBAppResource.string(R.string.leg_lift_description))
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            getContext().startActivity(intent);
        }
    };
}
