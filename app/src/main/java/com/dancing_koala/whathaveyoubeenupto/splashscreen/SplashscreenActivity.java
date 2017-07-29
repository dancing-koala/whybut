package com.dancing_koala.whathaveyoubeenupto.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.WhybutApp;
import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;
import com.dancing_koala.whathaveyoubeenupto.entry.EntryListActivity;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;
import com.dancing_koala.whathaveyoubeenupto.splashscreen.mvp.ISplashscreenPresenter;
import com.dancing_koala.whathaveyoubeenupto.splashscreen.mvp.ISplashscreenView;
import com.dancing_koala.whathaveyoubeenupto.splashscreen.mvp.SplashscreenPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashscreenActivity extends AppCompatActivity implements ISplashscreenView {

    private static final long SPLASH_DURATION = 1500L;
    private static final long RECHECK_IF_DONE_DELAY = 250L;

    @BindView(R.id.icon_wrapper)
    View mIconWrapper;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private boolean mCanGoToNextScreen;
    private Handler mHandler;
    private ISplashscreenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        ButterKnife.bind(this);
        mHandler = new Handler();
        mPresenter = new SplashscreenPresenter(new EntryRepository(((WhybutApp) getApplication()).getDaoSession()), SettingsManager.getInstance());

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCanGoToNextScreen) {
                    navigateToNextScreen();
                    return;
                }

                mHandler.postDelayed(this, RECHECK_IF_DONE_DELAY);
            }
        }, SPLASH_DURATION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.executeTasks();
            }
        }, RECHECK_IF_DONE_DELAY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    public void showProgressBar(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void canNavigateToNextScreen() {
        mCanGoToNextScreen = true;
        showProgressBar(false);
    }

    @Override
    public void displayTasksStartedIndicator() {
        showProgressBar(true);
    }

    private void navigateToNextScreen() {
        Intent entriesIntent = new Intent(this, EntryListActivity.class);
        startActivity(entriesIntent);
        finish();
    }
}
