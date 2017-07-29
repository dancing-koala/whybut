package com.dancing_koala.whathaveyoubeenupto.application;

import android.app.Application;

import com.dancing_koala.whathaveyoubeenupto.BuildConfig;
import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.utils.DateTimeUtils;
import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoMaster;
import com.dancing_koala.whathaveyoubeenupto.data.dao.DaoSession;
import com.dancing_koala.whathaveyoubeenupto.settings.SettingsManager;

import org.greenrobot.greendao.database.Database;

/**
 * Created by dancing_koala on 14/07/17.
 */

public class WhybutApp extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, BuildConfig.DB_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();

        SettingsManager.initialize(this, getString(R.string.preferences_filename));
        DateTimeUtils.init(getString(R.string.default_date_format));
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
