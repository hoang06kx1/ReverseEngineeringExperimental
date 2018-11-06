package app.giaotieptienghan.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.giaotieptienghan.Utils;
import app.giaotieptienghan.cipher.C0767a;
import app.giaotieptienghan.cipher.Decryption;
import app.giaotieptienghan.cipher.ObfuscatedString;
import app.giaotieptienghan.model.GrammaItem;

/* renamed from: com.example.english.b.d */
public class EndlessloveDB1 {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private SQLiteDatabase sqLiteDatabase;
    /* renamed from: c */
    private DatabaseHelper1 DBHelper1;
    //private Decryption decryption;
    private C0767a revertCipher;

    public EndlessloveDB1(Context context) {
        try {
            this.context = context;
            this.DBHelper1 = new DatabaseHelper1(this.context);
            //this.decryption = new Decryption(new ObfuscatedString(new long[]{2679435343808083287L, -7417836030320947728L, 168300512791597831L}).toString());
            this.revertCipher = new C0767a(new ObfuscatedString(new long[]{3297864285793973364L, -6749867354775971907L, 683554246847815286L, -3535878960802658041L}).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public EndlessloveDB1 mo2881a() {
            this.DBHelper1.mo2866a();
            return this;
    }

    /* renamed from: a */
    public ArrayList<GrammaItem> mo2882a(int i) {
        ArrayList<GrammaItem> arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder;
            String str = "SELECT * FROM np";
            if (i != -1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" where favorites=");
                stringBuilder.append(i);
                str = stringBuilder.toString();
            }
            Cursor rawQuery = this.sqLiteDatabase.rawQuery(str, null);
            stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("DataAdapter", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    GrammaItem grammaItem = new GrammaItem();
                    grammaItem.id = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
                    grammaItem.phrase = rawQuery.getString(rawQuery.getColumnIndex("word"));
                    try {
                        grammaItem.description = this.revertCipher.mo2891a(rawQuery.getString(rawQuery.getColumnIndex("content")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    grammaItem.favorites = rawQuery.getInt(rawQuery.getColumnIndex("favorites"));
                    arrayList.add(grammaItem);
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } catch (SQLException e2) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getTestData >>");
            stringBuilder2.append(e2.toString());
            Log.e("DataAdapter", stringBuilder2.toString());
            throw e2;
        }
    }

    /* renamed from: a */
    public boolean mo2883a(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE np SET favorites=");
        stringBuilder.append(i);
        stringBuilder.append(" WHERE id=");
        stringBuilder.append(i2);
        try {
            this.sqLiteDatabase.execSQL(stringBuilder.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public EndlessloveDB1 mo2884b() {
        try {
            this.DBHelper1.isDatabaseOpenable();
            this.DBHelper1.close();
            this.sqLiteDatabase = this.DBHelper1.getReadableDatabase();
            return this;
        } catch (SQLException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("open >>");
            stringBuilder.append(e.toString());
            Log.e("DataAdapter", stringBuilder.toString());
            throw e;
        }
    }

    /* renamed from: c */
    public void mo2885c() {
        this.DBHelper1.close();
    }
}
