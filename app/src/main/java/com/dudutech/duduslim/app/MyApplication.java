package com.dudutech.duduslim.app;

import android.app.Application;

import com.dudutech.duduslim.utils.Cfg;
import com.dudutech.duduslim.utils.UIUtils;

/**
 * Created by winbin on 2014/5/8.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UIUtils.init(getApplicationContext(), Cfg.accentColor, Cfg.accentSecondaryColor, true, true, true, true);
    }
}
