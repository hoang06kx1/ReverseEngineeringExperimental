package app.giaotieptienghan.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.example.english.b.a */
public class DatabaseHelper extends SQLiteOpenHelper { // enlesslove.dat helper
    /* renamed from: a */
    private static String TAG = "DataBaseHelper";
    /* renamed from: b */
    private static String filePath = "";
    /* renamed from: c */
    private static String fileName = "endlesslovejp.db";
    /* renamed from: d */
    private SQLiteDatabase sqLiteDatabase;
    /* renamed from: e */
    private final Context context;
    /* renamed from: f */
    private AppPreference appPreference;

    public DatabaseHelper(Context context) {
        super(context, fileName, null, 22);
        StringBuilder stringBuilder;
        String str;
        if (((double) VERSION.SDK_INT) >= 4.2d) {
            stringBuilder = new StringBuilder();
            str = context.getApplicationInfo().dataDir;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("/data/data/");
            str = context.getPackageName();
        }
        stringBuilder.append(str);
        stringBuilder.append("/databases/");
        filePath = stringBuilder.toString();
        this.context = context;
        this.appPreference = new AppPreference(context);
    }

    /* renamed from: c */
    private boolean isFileExist() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(filePath);
        stringBuilder.append(fileName);
        return new File(stringBuilder.toString()).exists();
    }

    /* renamed from: d */
    private void copyFileToDatabaseLocation() throws IOException {
        InputStream open = this.context.getAssets().open(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(filePath);
        stringBuilder.append(fileName);
        OutputStream fileOutputStream = new FileOutputStream(stringBuilder.toString());
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                open.close();
                this.appPreference.saveDBVersion(22);
                return;
            }
        }
    }

    /* renamed from: a */
    public void tryCreateDatabase() {
        if (!(!isFileExist() || 22 == this.appPreference.getDBVersion() || this.context.getDatabasePath(fileName).delete())) {
            Log.w(TAG, "Unable to update database");
        }
        if (!isFileExist()) {
            getReadableDatabase();
            close();
            try {
                copyFileToDatabaseLocation();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException unused) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    /* renamed from: b */
    @SuppressLint("WrongConstant")
    public boolean isDatabaseOpenable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(filePath);
        stringBuilder.append(fileName);
        this.sqLiteDatabase = SQLiteDatabase.openDatabase(stringBuilder.toString(), null, 268435456);
        return this.sqLiteDatabase != null;
    }

    public synchronized void close() {
        if (this.sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
