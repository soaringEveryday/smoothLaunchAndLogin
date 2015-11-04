package com.soaring.smoothlaunchandlogin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * First page after login into
 * <p/>
 */
public class DesktopActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int ANIMATION_DURATION_ENTER = 700;

    @Bind(R.id.tv_operator)
    TextView tvOperator;
    @Bind(R.id.iv_job)
    TextView ivJob;
    @Bind(R.id.iv_date)
    TextView ivDate;
    @Bind(R.id.btn_purchase)
    ImageButton btnPurchase;
    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.bottom)
    LinearLayout bottom;
    @Bind(R.id.fl_purchase)
    FrameLayout flPurchase;

    private ArrayList<Integer> viewIds;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        mContext = this;
        ButterKnife.bind(this);
        initView();

        if (top != null) {
            top.post(new Runnable() {
                @Override
                public void run() {
                    startEnterAnimation();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void startEnterAnimation() {
        int actionbarSize = DensityUtils.dp2px(this, top.getHeight());

        top.setTranslationY(-actionbarSize);
        top.animate()
                .translationY(0)
                .setDuration(200)
                .setInterpolator(new DecelerateInterpolator())
                .setStartDelay(100);


        int bottomHeight = DensityUtils.dp2px(this, bottom.getHeight());
        bottom.setTranslationY(bottomHeight);
        bottom.animate()
                .translationY(0)
                .setDuration(ANIMATION_DURATION_ENTER)
                .setInterpolator(new DecelerateInterpolator())
                .setStartDelay(200);

        int lineWidth = DensityUtils.dp2px(this, line.getWidth());
        line.setTranslationX(-lineWidth);
        line.animate()
                .translationX(0)
                .setDuration(ANIMATION_DURATION_ENTER)
                .setInterpolator(new DecelerateInterpolator())
                .setStartDelay(300);
    }

    private void initView() {

        ivDate.setText(getFormattedPendingTime(System.currentTimeMillis()));
        bindViewClick();
    }

    private void bindViewClick() {
        viewIds = new ArrayList<>();
        viewIds.add(R.id.btn_cashier);
        viewIds.add(R.id.btn_goods);
        viewIds.add(R.id.btn_purchase);
        viewIds.add(R.id.btn_reports);
        viewIds.add(R.id.btn_member);
        viewIds.add(R.id.btn_settings);
        for (int viewId : viewIds) {
            findViewById(viewId).setOnClickListener(this);
        }
    }

    private String getFormattedPendingTime(Long time) {
        String returnStr;
        String TIME_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        Date date = new Date(time);
        returnStr = sdf.format(date);
        return returnStr;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
