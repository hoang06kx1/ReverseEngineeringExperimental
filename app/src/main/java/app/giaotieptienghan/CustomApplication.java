package app.giaotieptienghan;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, (BuildConfig.DEBUG) ? AppConfig.debugAdmobAppId : AppConfig.releaseAdmobAppId);
    }
}
