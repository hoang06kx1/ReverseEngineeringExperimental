package app.giaotieptienghan.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;

import app.giaotieptienghan.cipher.C0767a;
import app.giaotieptienghan.cipher.Decryption;
import app.giaotieptienghan.model.GrammaItem;
import app.giaotieptienghan.repository.DatabaseHelper1;

/* renamed from: com.example.english.b.d */
public class EndlessloveDB1 {
    /* renamed from: a */
    private Context f1951a;
    /* renamed from: b */
    private SQLiteDatabase f1952b;
    /* renamed from: c */
    private DatabaseHelper1 f1953c;

    public EndlessloveDB1(Context context) {
        try {
            this.f1951a = context;
            this.f1953c = new DatabaseHelper1(this.f1951a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public EndlessloveDB1 mo2881a() {
            this.f1953c.mo2866a();
            return this;
    }

    /* renamed from: a */
    public ArrayList<GrammaItem> mo2882a(int i) {
        ArrayList<GrammaItem> arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder;
            String str = "SELECT * FROM phrases";
            if (i != -1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" where favorites=");
                stringBuilder.append(i);
                str = stringBuilder.toString();
            }
            Cursor rawQuery = this.f1952b.rawQuery(str, null);
            stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("DataAdapter", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    GrammaItem grammaItem = new GrammaItem();
                    grammaItem.id = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
                    grammaItem.phrase = rawQuery.getString(rawQuery.getColumnIndex("phrase"));
                    try {
                        grammaItem.description = new C0767a().mo2891a(rawQuery.getString(rawQuery.getColumnIndex("description")));
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
        stringBuilder.append("UPDATE phrases SET favorites=");
        stringBuilder.append(i);
        stringBuilder.append(" WHERE _id=");
        stringBuilder.append(i2);
        try {
            this.f1952b.execSQL(stringBuilder.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public EndlessloveDB1 mo2884b() {
        try {
            this.f1953c.isDatabaseOpenable();
            this.f1953c.close();
            this.f1952b = this.f1953c.getReadableDatabase();
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
        this.f1953c.close();
    }
}
