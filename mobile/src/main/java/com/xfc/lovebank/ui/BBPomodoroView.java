package com.xfc.lovebank.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.xfc.lovebank.R;
import com.xfc.lovebank.app.BBLoveBankApplication;
import com.xfc.lovebank.manager.BBNotificationManager;
import com.xfc.lovebank.manager.SoundEngine;
import com.xfc.lovebank.manager.BBStatisticsManager;
import com.xfc.lovebank.ui.base.BBBaseView;
import com.xfc.lovebank.utils.BBAppResource;
import com.xfc.lovebank.utils.CONST_VALUES;
import com.xfc.lovebank.utils.CountDownTimer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangzf
 * @datetime 20 Nov 2014, 10:29 AM
 */
// TODO switch to update listener from state
// TODO calendar delegate
public class BBPomodoroView extends BBBaseView {
    private TextView timerDisplay;
    private ImageButton timerStart;
    private EditText taskEdit;
    private CountDownTimer timer = null;
    private Calendar beginTime;
    private long millionBegin;
    private long millionsUntilFinish;



    public BBPomodoroView(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View rootView = getInflater().inflate(R.layout.fragment_pomotodo, this, false);
        timerDisplay = (TextView) rootView.findViewById(R.id.time_display);
        timerStart = (ImageButton) rootView.findViewById(R.id.timer_start);
        taskEdit = (EditText) rootView.findViewById(R.id.task_edit);
        timerStart.setOnClickListener(timerStartCLickListener);

        millionsUntilFinish = BBStatisticsManager.getInstance().getMillionsUntilFinish();
        millionBegin = BBStatisticsManager.getInstance().getMillionBegin();
        if (millionsUntilFinish != 0
                && millionBegin != 0) {
            timerStart.performClick();
        }
        return rootView;
    }

    private OnClickListener timerStartCLickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            beginTime = Calendar.getInstance();
            if (millionBegin == 0) {
                millionBegin = System.currentTimeMillis();
            } else {
                int passed = (int)(millionBegin - System.currentTimeMillis());
                beginTime.add(Calendar.MILLISECOND, passed);
            }

            if (millionsUntilFinish <= 0) {
                millionsUntilFinish = CONST_VALUES.TWENTY_MINUTES;
            } else {
                millionsUntilFinish += millionBegin - System.currentTimeMillis();
            }
            timer = new CountDownTimer(millionsUntilFinish, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    millionsUntilFinish = millisUntilFinished;
                    Date date = new Date(millisUntilFinished);
                    DateFormat formatter = new SimpleDateFormat("mm:ss");
                    String format = formatter.format(date);
                    timerDisplay.setText(format);
                    SoundEngine.sharedEngine().playEffect(getContext(), R.raw.tick_deep_frozen_apps_397275646);
                }

                @Override
                public void onFinish() {
                    timerStart.setImageDrawable(BBAppResource.drawable(R.drawable.photo_selected));
                    timerStart.setOnClickListener(timerEndClickListener);
                    SoundEngine.sharedEngine().playEffect(getContext(), R.raw.cuckoo_clock);

                    BBNotificationManager.getInstance().notify(1, buildNotification(getContext()));
                }
            }.start();

            timerStart.setImageDrawable(BBAppResource.drawable(R.drawable.timer_stop));
            timerStart.setOnClickListener(timerStartedCLickListener);
        }
    };

    private OnClickListener timerStartedCLickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            timerDisplay.setText(BBAppResource.string(R.string.twenty_file_minutes));
            timer.cancel();
            millionBegin = 0L;
            millionsUntilFinish = CONST_VALUES.TWENTY_MINUTES;
            timerStart.setImageDrawable(BBAppResource.drawable(R.drawable.play_icon));
            timerStart.setOnClickListener(timerStartCLickListener);
        }
    };

    private OnClickListener timerEndClickListener = new OnClickListener() {
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        @Override
        public void onClick(View view) {
            if (taskEdit.getText().toString().equals("")) {
                Toast.makeText(getContext(), R.string.notify_task_is_null, Toast.LENGTH_LONG).show();
                return;
            }
            Calendar endTime = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, taskEdit.getText() + CONST_VALUES.title_identifier)
                    .putExtra(CalendarContract.Events.DESCRIPTION, taskEdit.getText())
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                    .putExtra(CalendarContract.Events.ALLOWED_REMINDERS, CalendarContract.Reminders.METHOD_DEFAULT);
            getContext().startActivity(intent);
            BBStatisticsManager.getInstance().increacePomoSession();

            int restDuration = CONST_VALUES.FIVE_MINUTES;
            if (BBStatisticsManager.getInstance().getPomoSessionCnt() % 4 == 0) {
                restDuration = CONST_VALUES.FIFTEEN_MINUTES;
            }

            timerStart.setOnClickListener(null);
            CountDownTimer restTimer = new CountDownTimer(restDuration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Date date = new Date(millisUntilFinished);
                    DateFormat formatter = new SimpleDateFormat("mm:ss");
                    String format = formatter.format(date);
                    timerDisplay.setText(format);
                }

                @Override
                public void onFinish() {
                    timerStart.setImageDrawable(BBAppResource.drawable(R.drawable.play_icon));
                    timerStart.setOnClickListener(timerStartCLickListener);
                    millionBegin = 0L;
                    millionsUntilFinish = CONST_VALUES.TWENTY_MINUTES;

                }
            }.start();
        }
    };

    @Override
    public void saveState() {
        super.saveState();
        BBStatisticsManager.getInstance().setMillionBegin(millionBegin);
        BBStatisticsManager.getInstance().setMillionUntilFinish(millionsUntilFinish);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private Notification buildNotification(Context context) {
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(BBAppResource.string(R.string.title_pomodoro));

        Intent intent = new Intent(BBLoveBankApplication.getApplication(), BBLoveBankApplication.getHomeActivity());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        bigText.setSummaryText(BBAppResource.string(R.string.twenty_file_minutes_rest));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(BBAppResource.bitmap(R.drawable.alex));
        builder.setSmallIcon(R.drawable.alex)
                .setContentTitle(BBAppResource.string(R.string.title_pomodoro))
                .setContentText(BBAppResource.string(R.string.twenty_file_minutes_rest))
                .setNumber(1)
                .setAutoCancel(true)
                .setStyle(bigText).setContentIntent(pendingIntent)
                .setVibrate(new long[]{0, 200, 500, 200, 500})
                .setTicker(BBAppResource.string(R.string.twenty_file_minutes_rest))
                .setSound(BBAppResource.getSourceUri(R.raw.cuckoo_clock), AudioManager.STREAM_NOTIFICATION);
        return builder.build();
    }

    @Override
    public void onHidView() {
        super.onHidView();
    }
}
