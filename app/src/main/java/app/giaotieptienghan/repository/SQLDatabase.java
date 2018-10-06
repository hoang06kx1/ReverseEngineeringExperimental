package app.giaotieptienghan.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;

import app.giaotieptienghan.cipher.Decryption;
import app.giaotieptienghan.cipher.ObfuscatedString;
import app.giaotieptienghan.model.PhraseItem;

/* renamed from: com.example.english.b.c */
public class SQLDatabase {
    /* renamed from: a */
    private Context f1947a;
    /* renamed from: b */
    private SQLiteDatabase f1948b;
    /* renamed from: c */
    private C0762a f1949c;
    /* renamed from: d */
    private Decryption f1950d;

    public SQLDatabase(Context context) {
        try {
            this.f1947a = context;
            this.f1949c = new C0762a(this.f1947a);
            this.f1950d = new Decryption(new ObfuscatedString(new long[]{54233370504433150L, 4898902494104535733L, -5807597149084756791L, 8156855886985688596L}).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private PhraseItem m2972a(Cursor cursor) {
        PhraseItem phraseItem = new PhraseItem();
        phraseItem.f2071id = cursor.getInt(cursor.getColumnIndex("_id"));
        phraseItem.categoryId = cursor.getString(cursor.getColumnIndex("category_id"));
        phraseItem.english = cursor.getString(cursor.getColumnIndex("english"));
        phraseItem.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
        phraseItem.korean = this.f1950d.mo2918a(cursor.getString(cursor.getColumnIndex("korean"))).replace("# ", ", ");
        phraseItem.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
        phraseItem.voice = cursor.getString(cursor.getColumnIndex("voice"));
        phraseItem.status = cursor.getString(cursor.getColumnIndex("status"));
        phraseItem.vietnamese = cursor.getString(cursor.getColumnIndex("vietnamese"));
        phraseItem.chinese = cursor.getString(cursor.getColumnIndex("chinese"));
        phraseItem.search = cursor.getString(cursor.getColumnIndex("search"));
        return phraseItem;
    }

    /* renamed from: a */
    private PhraseItem m2973a(Cursor cursor, boolean z) {
        String str;
        PhraseItem phraseItem = new PhraseItem();
        phraseItem.f2071id = cursor.getInt(cursor.getColumnIndex("_id"));
        phraseItem.categoryId = cursor.getString(cursor.getColumnIndex("category_id"));
        phraseItem.english = cursor.getString(cursor.getColumnIndex("english"));
        phraseItem.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
        phraseItem.korean = this.f1950d.mo2918a(cursor.getString(cursor.getColumnIndex("korean"))).replace("# ", ", ");
        phraseItem.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
        if (z) {
            phraseItem.voice = cursor.getString(cursor.getColumnIndex("vietnamese")).replace("honhan", "honnhan");
            str = "voice";
        } else {
            phraseItem.voice = cursor.getString(cursor.getColumnIndex("voice"));
            str = "vietnamese";
        }
        phraseItem.vietnamese = cursor.getString(cursor.getColumnIndex(str));
        phraseItem.status = cursor.getString(cursor.getColumnIndex("status"));
        phraseItem.search = cursor.getString(cursor.getColumnIndex("search"));
        phraseItem.chinese = cursor.getString(cursor.getColumnIndex("chinese"));
        return phraseItem;
    }

    /* renamed from: b */
    private PhraseItem m2974b(Cursor cursor) {
        PhraseItem phraseItem = new PhraseItem();
        phraseItem.f2071id = cursor.getInt(cursor.getColumnIndex("_id"));
        phraseItem.categoryId = String.valueOf(cursor.getInt(cursor.getColumnIndex("cateId")));
        phraseItem.korean = this.f1950d.mo2918a(cursor.getString(cursor.getColumnIndex("korean")));
        phraseItem.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
        phraseItem.vietnamese = cursor.getString(cursor.getColumnIndex("vietnamese"));
        phraseItem.search = cursor.getString(cursor.getColumnIndex("search"));
        return phraseItem;
    }

    /* renamed from: a */
    public C0764c mo2871a() {
        try {
            this.f1949c.mo2861a();
            return this;
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e.toString());
            stringBuilder.append("  UnableToCreateDatabase");
            Log.e("GetData", stringBuilder.toString());
            throw new Error("UnableToCreateDatabase");
        }
    }

    /* renamed from: a */
    public ArrayList<PhraseItem> mo2872a(int i) {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            String str = "SELECT * FROM phrase";
            if (i != -1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" where category_id=");
                stringBuilder.append(i);
                str = stringBuilder.toString();
            }
            Cursor rawQuery = this.f1948b.rawQuery(str, null);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("c ");
            stringBuilder2.append(rawQuery);
            Log.e("GetData", stringBuilder2.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(i == 33 ? m2973a(rawQuery, true) : m2972a(rawQuery));
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } catch (SQLException e) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("getTestData >>");
            stringBuilder3.append(e.toString());
            Log.e("GetData", stringBuilder3.toString());
            throw e;
        }
    }

    /* renamed from: a */
    public ArrayList<CategoryItem> mo2873a(String str) {
        ArrayList<CategoryItem> arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder;
            String str2 = "SELECT * FROM category";
            if (!C0778h.m3033a(str)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(" where _id=");
                stringBuilder.append(str);
                str2 = stringBuilder.toString();
            }
            Cursor rawQuery = this.f1948b.rawQuery(str2, null);
            stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("GetData", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    CategoryItem categoryItem = new CategoryItem();
                    categoryItem.f2068id = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
                    categoryItem.english = rawQuery.getString(rawQuery.getColumnIndex("english"));
                    categoryItem.vietnamese = rawQuery.getString(rawQuery.getColumnIndex("vietnamese"));
                    categoryItem.chinese = rawQuery.getString(rawQuery.getColumnIndex("chinese"));
                    arrayList.add(categoryItem);
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } catch (SQLException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getTestData >>");
            stringBuilder2.append(e.toString());
            Log.e("GetData", stringBuilder2.toString());
            throw e;
        }
    }

    /* renamed from: a */
    public boolean mo2874a(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE phrase SET favorite=");
        stringBuilder.append(i);
        stringBuilder.append(" WHERE _id=");
        stringBuilder.append(i2);
        try {
            this.f1948b.execSQL(stringBuilder.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public C0764c mo2875b() {
        try {
            this.f1949c.mo2862b();
            this.f1949c.close();
            this.f1948b = this.f1949c.getReadableDatabase();
            return this;
        } catch (SQLException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("open >>");
            stringBuilder.append(e.toString());
            Log.e("GetData", stringBuilder.toString());
            throw e;
        }
    }

    /* renamed from: b */
    public ArrayList<PhraseItem> mo2876b(int i) {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder;
            String str = "SELECT * FROM sub";
            if (i != -1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" where cateId=");
                stringBuilder.append(i);
                str = stringBuilder.toString();
            }
            Cursor rawQuery = this.f1948b.rawQuery(str, null);
            stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("GetData", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(m2974b(rawQuery));
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } catch (SQLException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getTestData >>");
            stringBuilder2.append(e.toString());
            Log.e("GetData", stringBuilder2.toString());
            throw e;
        }
    }

    /* renamed from: b */
    public boolean mo2877b(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE sub SET favorite=");
        stringBuilder.append(i);
        stringBuilder.append(" WHERE _id=");
        stringBuilder.append(i2);
        try {
            this.f1948b.execSQL(stringBuilder.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public void mo2878c() {
        this.f1949c.close();
    }

    /* renamed from: d */
    public ArrayList<PhraseItem> mo2879d() {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            Cursor rawQuery = this.f1948b.rawQuery("SELECT * FROM sub where favorite=1", null);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("GetData", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(m2974b(rawQuery));
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } catch (SQLException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getTestData >>");
            stringBuilder2.append(e.toString());
            Log.e("GetData", stringBuilder2.toString());
            throw e;
        }
    }

    /* renamed from: e */
    public ArrayList<PhraseItem> mo2880e() {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            Cursor rawQuery = this.f1948b.rawQuery("SELECT * FROM phrase where favorite=1", null);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("GetData", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(m2972a(rawQuery));
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } catch (SQLException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getTestData >>");
            stringBuilder2.append(e.toString());
            Log.e("GetData", stringBuilder2.toString());
            throw e;
        }
    }
}
