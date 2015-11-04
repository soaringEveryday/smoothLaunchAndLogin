package com.soaring.smoothlaunchandlogin;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends Activity {

    private static final int ANIMATION_DURATION_ENTER = 700;

    @Bind(R.id.launch_circle)
    ImageView launchCircle;
    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.launch_slogan)
    ImageView launchSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        launchCircle.setRotation(0f);
        launchSlogan.setAlpha(0f);
        launchCircle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation rotateAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_launch);
                DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
                rotateAnim.setInterpolator(decelerateInterpolator);

                launchCircle.startAnimation(rotateAnim);

                launchSlogan.animate().alpha(1f)
                        .setDuration(1100)
                        .setStartDelay(100)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                launchCircle.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        enterLogin();
                                    }
                                }, 900);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
            }
        }, 1200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void enterLogin() {

        int bottomHeight = DensityUtils.dp2px(this, root.getHeight());
        root.animate()
                .translationY(-bottomHeight * 1.2f)
                .setDuration(ANIMATION_DURATION_ENTER)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .setStartDelay(100);

    }
}
