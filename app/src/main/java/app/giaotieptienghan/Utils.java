package app.giaotieptienghan;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import app.giaotieptienghan.model.BaseData;

/* renamed from: com.example.english.c.h */
@SuppressLint("WrongConstant")
public class Utils {

    /* renamed from: com.example.english.c.h$2 */
    static class C07772 extends AnimatorListenerAdapter {
        C07772() {
        }

        public void onAnimationEnd(Animator animator) {
        }
    }

    /* renamed from: a */
    public static int m3026a(Context context) {
        return Utils.getDisplayMetric(context).widthPixels;
    }

    /* renamed from: a */
    public static Builder m3027a(Context context, String str, CharSequence[] charSequenceArr, int i, OnClickListener onClickListener) {
        Builder builder = new Builder(context, VERSION.SDK_INT >= 23 ? R.style.AppCompatAlertDialogStyle : 3);
        builder.setTitle(str);
        builder.setSingleChoiceItems(charSequenceArr, i, onClickListener);
        return builder;
    }

    @SuppressLint({"UseValueOf"})
    /* renamed from: a */
    public static Class<?> m3028a(Field field) {
        return ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass()) ? (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0] : new Boolean(false).getClass();
    }

    /* renamed from: a */
    public static Object m3029a(Class<? extends BaseData> cls, JSONObject jSONObject) {
        Object newInstance;
        Exception e;
        try {
            newInstance = cls.newInstance();
            try {
                Field[] fields = cls.getFields();
                if (fields != null && fields.length > 0) {
                    for (Field field : fields) {
                        try {
                            if (jSONObject.has(field.getName().trim())) {
                                Object string = null;
                                Class type = field.getType();
                                if (type == String.class) {
                                    string = jSONObject.getString(field.getName().trim());
                                } else if (type == Integer.TYPE) {
                                    string = Integer.valueOf(jSONObject.getInt(field.getName().trim()));
                                } else if (Collection.class.isAssignableFrom(type)) {
                                    type = Utils.m3028a(field);
                                    if (type.getSuperclass().isAssignableFrom(cls) && jSONObject.has(field.getName())) {
                                        ArrayList arrayList;
                                        try {
                                            JSONArray jSONArray = jSONObject.getJSONArray(field.getName());
                                            arrayList = new ArrayList();
                                            for (int i = 0; i < jSONArray.length(); i++) {
                                                arrayList.add((BaseData) Utils.m3029a(type, jSONArray.getJSONObject(i)));
                                            }
                                            field.set(newInstance, arrayList);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                            JSONObject jSONObject2 = jSONObject.getJSONObject(field.getName());
                                            arrayList = new ArrayList();
                                            if (jSONObject2 != null) {
                                                arrayList.add((BaseData) Utils.m3029a(type, jSONObject2));
                                                field.set(newInstance, arrayList);
                                            }
                                        }
                                    }
                                }
                                field.set(newInstance, string);
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Exception e5) {
            e = e5;
            newInstance = null;
            e.printStackTrace();
            return newInstance;
        }
        return newInstance;
    }

    /* renamed from: a */
    public static void m3030a(Context context, int i) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent("com.fun.korean.alarmaction"), 268435458);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.set(11, 7);
        instance.set(12, 0);
        instance.set(13, 0);
        alarmManager.setRepeating(0, instance.getTimeInMillis(), 3600000 * ((long) i), broadcast);
        System.out.println("setAlarm");
    }

    /* renamed from: a */
    public static void m3031a(Context context, String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://play.google.com/store/apps/details?id=");
            stringBuilder.append(context.getPackageName());
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("share ");
            stringBuilder3.append(str);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.setType("text/plain");
            List arrayList = new ArrayList();
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                String str2 = resolveInfo.activityInfo.packageName;
                if (str2.contains("facebook") || str2.contains("twitter") || str2.contains("mail") || str2.contains("gm") || str2.contains("sms")) {
                    Intent intent2 = new Intent();
                    intent2.setComponent(new ComponentName(str2, resolveInfo.activityInfo.name));
                    intent2.setAction("android.intent.action.SEND");
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(str);
                    stringBuilder4.append("\n");
                    stringBuilder4.append(stringBuilder2);
                    intent2.putExtra("android.intent.extra.TEXT", stringBuilder4.toString());
                    intent2.putExtra("android.intent.extra.STREAM", "Học tiếng hàn giao tiếp");
                    intent2.setType("text/plain");
                    intent2.setPackage(str2);
                    arrayList.add(intent2);
                }
            }
            Intent createChooser = Intent.createChooser((Intent) arrayList.remove(0), "Share");
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            context.startActivity(createChooser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void animateTextView(final View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{16.0f, 28.0f, 16.0f});
        ofFloat.setDuration(200);
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (view instanceof TextView) {
                    ((TextView) view).setTextSize(floatValue);
                    return;
                }
                if (view instanceof EditText) {
                    ((EditText) view).setTextSize(floatValue);
                }
            }
        });
        ofFloat.addListener(new C07772());
        ofFloat.setRepeatCount(1);
        ofFloat.start();
    }

    /* renamed from: a */
    public static boolean isStringEmpty(String str) {
        if (str != null) {
            return "".equals(str) || "null".equals(str);
        } else {
            return true;
        }
    }

    /* renamed from: a */
    public static int[] m3034a(long j) {
        try {
            int[] iArr = new int[2];
            int i = (int) (j / 60);
            int i2 = (int) (j - ((long) (i * 60)));
            iArr[0] = i;
            iArr[1] = i2;
            return iArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static int getScreenHeight(Context context) {
        return Utils.getDisplayMetric(context).heightPixels;
    }

    /* renamed from: b */
    public static int getColor(Context context, int i) {
        return VERSION.SDK_INT >= 23 ? context.getResources().getColor(i, null) : context.getResources().getColor(i);
    }

    /* renamed from: c */
    public static Drawable getDrawable(Context context, int i) {
        return VERSION.SDK_INT >= 21 ? context.getResources().getDrawable(i, null) : context.getResources().getDrawable(i);
    }

    /* renamed from: c */
    public static boolean m3038c(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        int type = activeNetworkInfo.getType();
        return (type == 1 || type == 0) ? activeNetworkInfo.isConnected() : false;
    }

    /* renamed from: d */

    public static void m3039d(Context context) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent("com.fun.korean.alarmaction"), 134217730);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Calendar instance = Calendar.getInstance();
        instance.set(11, 8);
        instance.set(12, 0);
        instance.set(13, 0);
        alarmManager.setRepeating(0, instance.getTimeInMillis(), 10800000, broadcast);
    }

    /* renamed from: e */
    @SuppressLint("WrongConstant")
    public static void m3040e(Context context) {
        try {
            ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, new Intent("com.fun.korean.alarmaction"), 268435458));
            System.out.println("AlarmManager update canceled. ");
        } catch (Exception e) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AlarmManager update was not canceled. ");
            stringBuilder.append(e.toString());
            printStream.println(stringBuilder.toString());
        }
    }

    /* renamed from: f */
    private static DisplayMetrics getDisplayMetric(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
