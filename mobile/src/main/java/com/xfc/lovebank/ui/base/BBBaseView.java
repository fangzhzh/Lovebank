package com.xfc.lovebank.ui.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.xfc.lovebank.utils.BBLeakFixing;

import java.lang.ref.WeakReference;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 4:41 PM
 */
public class BBBaseView extends LinearLayout {
    protected WeakReference<Activity> m_context;


    public BBBaseView(Context context) {
        super(context);
        m_context = new WeakReference<Activity>((Activity)context);
        __postCall(context);
    }

    public LayoutInflater getInflater() {
        return LayoutInflater.from(m_context.get());
    }

    public BBBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.setOnHierarchyChangeListener(this);
        __postCall(context);
    }

    private void __postCall(final Context context) {
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        /*BBUILoop.getInstance().post(new Runnable()
        {
            @Override
            public void run()
            {
                _onPostCreate(context);
            }
        });*/
        //_onPostCreate(context);
    }


    @Override
    protected void finalize() throws Throwable {
//        BBAppLogger.i("Free ActionView:%s", this);
        super.finalize();
    }


    @Override
    protected void onDetachedFromWindow() {
        BBLeakFixing.unbindDrawables(this);
        super.onDetachedFromWindow();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //BBUIControlNotificationManager.getInstance().onRootSizeChanged(this);
    }

    public View onCreateView() {
        return null;
    }
}