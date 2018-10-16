package app.giaotieptienghan.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.example.english.c.e */
public class AppPreference { // App preference
    /* renamed from: a */
    public final String f1968a = "com.fun.korean:f_launch";
    /* renamed from: b */
    public final String f1969b = "com.fun.korean:roman";
    /* renamed from: c */
    public final String f1970c = "com.fun.korean:admob";
    /* renamed from: d */
    public final String f1971d = "com.fun.korean:interstitial";
    /* renamed from: e */
    public final String f1972e = "com.fun.korean:showsplash";
    /* renamed from: f */
    public final String f1973f = "com.fun.korean:showbanner";
    /* renamed from: g */
    public final String f1974g = "com.fun.korean:title";
    /* renamed from: h */
    public final String f1975h = "com.fun.korean:count";
    /* renamed from: i */
    public final String f1976i = "com.fun.korean:notification";
    /* renamed from: j */
    public final String f1977j = "com.fun.korean:volumes";
    /* renamed from: k */
    private final String f1978k = "com.fun.korean:";
    /* renamed from: l */
    private final String f1979l = "com.fun.korean:share_preference";
    /* renamed from: m */
    private final String f1980m = "com.fun.korean:high_score";
    /* renamed from: n */
    private final String f1981n = "com.fun.korean:display_max";
    /* renamed from: o */
    private final String f1982o = "com.fun.korean:db_version";

    private final String SELECTED_PRACTICE_ID = "com.fun.korean:selected_practice";
    /* renamed from: p */
    private SharedPreferences sharedPreferences = null;
    /* renamed from: q */
    private Editor editor = null;

    public AppPreference(Context context) {
        this.sharedPreferences = context.getSharedPreferences("com.fun.korean:share_preference", 0);
        this.editor = this.sharedPreferences.edit();
    }

    public String getSelectedPracticeId() {
        return this.sharedPreferences.getString(SELECTED_PRACTICE_ID, "0");
    }

    public void setSelectedPracticeId(String str) {
        this.editor.putString(SELECTED_PRACTICE_ID, str);
        this.editor.commit();
    }

    /* renamed from: a */
    public int mo2896a() {
        return this.sharedPreferences.getInt("com.fun.korean:high_score", 0);
    }


    /* renamed from: a */
    public int mo2897a(String str) {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("com.fun.korean:high_score");
        stringBuilder.append(str);
        return sharedPreferences.getInt(stringBuilder.toString(), 0);
    }

    /* renamed from: a */
    public void mo2898a(int i) {
        this.editor.putInt("com.fun.korean:high_score", i);
        this.editor.commit();
    }

    /* renamed from: a */
    public void mo2899a(String str, int i) {
        Editor editor = this.editor;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("com.fun.korean:high_score");
        stringBuilder.append(str);
        editor.putInt(stringBuilder.toString(), i);
        this.editor.commit();
    }

    /* renamed from: a */
    public void mo2900a(boolean z) {
        this.editor.putBoolean("com.fun.korean:f_launch", z);
        this.editor.commit();
    }

    /* renamed from: b */
    public int getDBVersion() {
        return this.sharedPreferences.getInt("com.fun.korean:db_version", 5);
    }

    /* renamed from: b */
    public void saveDBVersion(int i) {
        this.editor.putInt("com.fun.korean:db_version", i);
        this.editor.commit();
    }

    /* renamed from: b */
    public void mo2903b(String str) {
        this.editor.putString("com.fun.korean:interstitial", str);
        this.editor.commit();
    }

    /* renamed from: b */
    public void mo2904b(boolean z) {
        this.editor.putBoolean("com.fun.korean:volumes", z);
        this.editor.commit();
    }

    /* renamed from: c */
    public void mo2905c(int i) {
        this.editor.putInt("com.fun.korean:notification", i);
        this.editor.commit();
    }

    /* renamed from: c */
    public void mo2906c(String str) {
        this.editor.putString("com.fun.korean:showsplash", str);
        this.editor.commit();
    }

    /* renamed from: c */
    public boolean mo2907c() {
        return this.sharedPreferences.getBoolean("com.fun.korean:f_launch", true);
    }

    /* renamed from: d */
    public void mo2908d(int i) {
        this.editor.putInt("com.fun.korean:count", i);
        this.editor.commit();
    }

    /* renamed from: d */
    public void mo2909d(String str) {
        this.editor.putString("com.fun.korean:showbanner", str);
        this.editor.commit();
    }

    /* renamed from: d */
    public boolean mo2910d() {
        return this.sharedPreferences.getBoolean("com.fun.korean:admob", true);
    }

    /* renamed from: e */
    public int mo2911e() {
        return this.sharedPreferences.getInt("com.fun.korean:notification", 1);
    }

    /* renamed from: e */
    public void mo2912e(String str) {
        this.editor.putString("com.fun.korean:title", str);
        this.editor.commit();
    }

    /* renamed from: f */
    public String mo2913f() {
        return this.sharedPreferences.getString("com.fun.korean:interstitial", "ca-app-pub-8172083498288402/4982313037");
    }

    /* renamed from: g */
    public String mo2914g() {
        return this.sharedPreferences.getString("com.fun.korean:showbanner", "1");
    }

    /* renamed from: h */
    public int mo2915h() {
        return this.sharedPreferences.getInt("com.fun.korean:count", 4);
    }

    /* renamed from: i */
    public boolean mo2916i() {
        return this.sharedPreferences.getBoolean("com.fun.korean:volumes", true);
    }

    /* renamed from: j */
    public boolean mo2917j() {
        return this.sharedPreferences.getBoolean("com.fun.korean:roman", true);
    }
}
