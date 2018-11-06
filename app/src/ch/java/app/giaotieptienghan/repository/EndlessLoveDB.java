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
import app.giaotieptienghan.model.CategoryItem;
import app.giaotieptienghan.model.PhraseItem;

/* renamed from: com.example.english.b.c */
public class EndlessLoveDB {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private SQLiteDatabase sqLiteDatabase;
    /* renamed from: c */
    private DatabaseHelper helper;
    /* renamed from: d */
    private Decryption decryption;

    private C0767a revertCipher;

    public EndlessLoveDB(Context context) {
        try {
            this.context = context;
            this.helper = new DatabaseHelper(this.context);
            this.decryption = new Decryption(new ObfuscatedString(Utils.ALOHA).toString());
            this.revertCipher = new C0767a(new ObfuscatedString(new long[]{10047167883570276L, -3936464861463571530L, 7479621358591293963L, 6776793961198095282L}).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private PhraseItem m2972a(Cursor cursor) {
        PhraseItem phraseItem = new PhraseItem();
        phraseItem.id = cursor.getInt(cursor.getColumnIndex("_id"));
        phraseItem.categoryId = cursor.getString(cursor.getColumnIndex("category_id"));
        phraseItem.english = cursor.getString(cursor.getColumnIndex("english"));
        phraseItem.pinyin = this.revertCipher.mo2891a(cursor.getString(cursor.getColumnIndex("pinyin")));
        phraseItem.korean = this.revertCipher.mo2891a(cursor.getString(cursor.getColumnIndex("chinese")));
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
        phraseItem.id = cursor.getInt(cursor.getColumnIndex("_id"));
        phraseItem.categoryId = cursor.getString(cursor.getColumnIndex("category_id"));
        phraseItem.english = cursor.getString(cursor.getColumnIndex("english"));
        phraseItem.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
        phraseItem.korean = this.decryption.decrypt(cursor.getString(cursor.getColumnIndex("japanese")));
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
        phraseItem.id = cursor.getInt(cursor.getColumnIndex("_id"));
        phraseItem.categoryId = String.valueOf(cursor.getInt(cursor.getColumnIndex("category_id")));
        try {
            Decryption c0774g = new Decryption(new ObfuscatedString(new long[]{54233370504433150L, 4898902494104535733L, -5807597149084756791L, 8156855886985688596L}).toString());
            phraseItem.pinyin = c0774g.decrypt(cursor.getString(cursor.getColumnIndex("pinyin")));
            phraseItem.korean = c0774g.decrypt(cursor.getString(cursor.getColumnIndex("chinese")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //phraseItem.korean = this.decryption.decrypt(cursor.getString(cursor.getColumnIndex("japanese")));
        phraseItem.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
        phraseItem.vietnamese = cursor.getString(cursor.getColumnIndex("vietnamese"));
        phraseItem.search = cursor.getString(cursor.getColumnIndex("search"));
        return phraseItem;
    }

    /* renamed from: a */
    public EndlessLoveDB initDB() {
        try {
            this.helper.tryCreateDatabase();
            return this;
        } catch (Exception ex) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ex.toString());
            stringBuilder.append("  UnableToCreateDatabase");
            Log.e("GetData", stringBuilder.toString());
            throw new Error("UnableToCreateDatabase");
        }
    }

    /* renamed from: a */
    public ArrayList<PhraseItem> getPhrasesByCategoryId(int i) {
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
            Cursor rawQuery = this.sqLiteDatabase.rawQuery(str, null);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("c ");
            stringBuilder2.append(rawQuery);
            Log.e("GetData", stringBuilder2.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(m2972a(rawQuery));
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
    public ArrayList<CategoryItem> getFavoriteCategories(String str) {
        ArrayList<CategoryItem> arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder;
            String str2 = "SELECT * FROM category";
            if (!Utils.isStringEmpty(str)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(" where _id=");
                stringBuilder.append(str);
                str2 = stringBuilder.toString();
            }
            Cursor rawQuery = this.sqLiteDatabase.rawQuery(str2, null);
            stringBuilder = new StringBuilder();
            stringBuilder.append("c ");
            stringBuilder.append(rawQuery);
            Log.e("GetData", stringBuilder.toString());
            if (rawQuery.moveToFirst()) {
                do {
                    CategoryItem categoryItem = new CategoryItem();
                    categoryItem.setId(rawQuery.getInt(rawQuery.getColumnIndex("_id")));
                    categoryItem.setEnglish(rawQuery.getString(rawQuery.getColumnIndex("english")));
                    categoryItem.setVietnamese(rawQuery.getString(rawQuery.getColumnIndex("vietnamese")));
                    categoryItem.setChinese(rawQuery.getString(rawQuery.getColumnIndex("chinese")));
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
    public boolean setPhraseFavorite(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE phrase SET favorite=");
        stringBuilder.append(i);
        stringBuilder.append(" WHERE _id=");
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
    public EndlessLoveDB getReadableDB() {
        try {
            this.helper.isDatabaseOpenable();
            this.helper.close();
            this.sqLiteDatabase = this.helper.getReadableDatabase();
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
    public ArrayList<PhraseItem> getGrammarsByCategoryId(int i) {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder;
            String str = "SELECT * FROM sub";
            if (i != -1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" where category_id=");
                stringBuilder.append(i);
                str = stringBuilder.toString();
            }
            Cursor rawQuery = this.sqLiteDatabase.rawQuery(str, null);
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
            this.sqLiteDatabase.execSQL(stringBuilder.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public void closeDB() {
        this.helper.close();
    }

    /* renamed from: d */
    public ArrayList<PhraseItem> getFavoriteGrammars() {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            Cursor rawQuery = this.sqLiteDatabase.rawQuery("SELECT * FROM sub where favorite=1", null);
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
    public ArrayList<PhraseItem> getFavoritePhrases() {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        try {
            Cursor rawQuery = this.sqLiteDatabase.rawQuery("SELECT * FROM phrase where favorite=1", null);
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
