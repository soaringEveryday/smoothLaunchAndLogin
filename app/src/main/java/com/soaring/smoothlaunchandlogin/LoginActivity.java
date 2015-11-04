package com.soaring.smoothlaunchandlogin;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final int ANIMATION_DURATION_ENTER = 1000;
    private static final String REQ_TAG = "login";
    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;

    @Bind(R.id.setting_username)
    EditText mInputNickName;

    @Bind(R.id.setting_empname)
    EditText mInputEmpName;

    @Bind(R.id.setting_password)
    EditText mInputPwd;

    @Bind(R.id.tv_error_msg)
    TextView mErrMsg;

    @Bind(R.id.progressbar)
    ProgressBar mProgressbar;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.logo)
    ImageView mLogo;

    @Bind(R.id.login_root)
    ResizeRelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        setInputZone();

        mRoot.setListener(new ResizeRelativeLayout.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                int change = -1;
                if (h < oldh) {
                    change = SMALLER;
                } else if (h > oldh) {
                    change = BIGGER;
                }
                Message msg = Message.obtain();
                msg.what = MSG_RESIZE;
                msg.arg1 = change;
                mHandler.sendMessage(msg);
            }
        });

        mRoot.post(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private class InputHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESIZE:
                    if (msg.arg1 == SMALLER) {
                        mLogo.setVisibility(View.GONE);
                    } else if (msg.arg1 == BIGGER) {
                        mLogo.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private InputHandler mHandler = new InputHandler();

    private void startEnterAnimation() {
        int bottomHeight = DensityUtils.dp2px(this, mRoot.getHeight());
        mRoot.setTranslationY(bottomHeight * 1.3f);
        mRoot.animate()
                .translationY(0)
                .setDuration(ANIMATION_DURATION_ENTER)
                .setInterpolator(new DecelerateInterpolator())
                .setStartDelay(200);
    }

    @OnClick(R.id.btn_login)
    public void tryLogin(View view) {

//        enterMainActivityWithAnim();
        startLoginRequestData();
    }

    private Context mContext;

    /**
     * start login request
     */
    private void startLoginRequestData() {
        String username = mInputNickName.getText().toString().trim();
        String empName = mInputEmpName.getText().toString().trim();
        String password = mInputPwd.getText().toString();


        if (!username.matches("[\u4e00-\u9fa5\\w-]+") || username.length() < 1 || username.length() > 15) {
            mErrMsg.setText(getString(R.string.login_username_format_error));
            shakeErrorInputUsername();
            return;
        }

        if (!password.matches("\\w+")) {
            mErrMsg.setText(getString(R.string.login_pwd_format_error));
            shakeErrorInputPwd();
            return;
        }

        try {
            //TODO
        } catch (Exception e) {
            //TODO
        }
    }

    private void setInputZone() {
        mInputNickName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                mErrMsg.setError("");
                mErrMsg.setText("");
                mInputPwd.setText("");
            }
        });

        mInputPwd.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                if (mInputPwd.getText().toString().length() > 0) {
//                    mWrapperUsername.setError("");
//                    mWrapperPwd.setError("");
                    mErrMsg.setText("");
                }
            }
        });
    }


    private void shakeErrorInputPwd() {
        mInputPwd.startAnimation(AnimationUtils.loadAnimation(this, R.anim.login_error_shake));
    }

    private void shakeErrorInputUsername() {
        mInputNickName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.login_error_shake));
    }

    private void enterMainActivityWithAnim() {
//        startActivity(new Intent(getApplicationContext(), DesktopActivity.class));
        overridePendingTransition(0, 0);
    }

}
