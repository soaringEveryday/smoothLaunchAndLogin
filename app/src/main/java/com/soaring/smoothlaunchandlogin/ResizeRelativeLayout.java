package com.soaring.smoothlaunchandlogin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ResizeRelativeLayout extends RelativeLayout {

    private OnSizeChangedListener listener;

    public void setListener(OnSizeChangedListener listener) {
        this.listener = listener;
    }

    public ResizeRelativeLayout(Context context) {
        super(context);
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface OnSizeChangedListener {
        void onSizeChanged(int w, int h, int oldw, int oldh);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (listener != null) {
            listener.onSizeChanged(w, h, oldw, oldh);
        }
    }
}
