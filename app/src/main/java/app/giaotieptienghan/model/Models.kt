package app.giaotieptienghan.model

import android.arch.persistence.room.*
import android.content.Context
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory
import io.reactivex.Single

class MenuItem {
    public var image: Int = 0
    public var name: String? = null
}

@Entity(tableName = "category")
class CategoryItem {
    var chinese: String? = null
    var english: String? = null
    var _id: Int = 0
    var vietnamese: String? = null
}

class PhraseExItem {
    var cateId: Int = 0
    var favorite: Int = 0
    /* renamed from: id */
    var f2070id: Int = 0
    var korean: String? = null
    var search: String? = null
    var vietnamese: String? = null
}

@Entity(tableName = "phrase")
class PhraseItem {
    @ColumnInfo(name = "category_id")
    var categoryId: String? = null
    var chinese: String? = null
    var english: String? = null
    var favorite: Int = 0
    var _id: Int = 0
    var korean: String? = null
    var pinyin: String? = null
    var search: String? = null
    var status: String? = null
    var vietnamese: String? = null
    var voice: String? = null
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): Single<List<CategoryItem>>
}

@Database(entities = [CategoryItem::class, PhraseItem::class], version = 1)
abstract class MainDB : RoomDatabase() {

    abstract fun CategoryDao(): CategoryDao

    companion object {
        private var INSTANCE: MainDB? = null

        fun getInstance(context: Context): MainDB? {
            if (INSTANCE == null) {
                synchronized(MainDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MainDB::class.java, "enlesslove.dat")
                            .allowMainThreadQueries()
                            .openHelperFactory(AssetSQLiteOpenHelperFactory())
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}