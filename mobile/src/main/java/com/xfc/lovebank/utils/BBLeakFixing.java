package com.xfc.lovebank.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 4:43 PM
 */
public class BBLeakFixing {
    public static void unbindDrawables(View view) {
        if (view == null) {
            return;
        }
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ImageView) {
            ImageView imgView = (ImageView) view;
            if (imgView.getDrawable() != null) {
                Drawable b = imgView.getDrawable();
                b.setCallback(null);
                imgView.setImageDrawable(null);
//                if (b instanceof BitmapDrawable) {
//                    BitmapDrawable bitmapDrawable = (BitmapDrawable) b;
//                    Bitmap bitmap = bitmapDrawable.getBitmap();
//                    bitmap.recycle();
//
//                }
            }
        } else if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            try {
                if ((view instanceof AdapterView<?>)) {
                    if (!(view instanceof ExpandableListView)) {
                        AdapterView<?> adapterView = (AdapterView<?>) view;
                        adapterView.setAdapter(null);
                    }
                } else {
                    ((ViewGroup) view).removeAllViews();
                }
            } catch (Exception e) {
                BBAppLogger.e(e);
            }
        }
    }
}
