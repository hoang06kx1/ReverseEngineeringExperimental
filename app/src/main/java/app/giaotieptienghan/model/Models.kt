package app.giaotieptienghan.model

import android.arch.persistence.room.*
import android.content.Context
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory
import com.huma.room_for_asset.RoomAsset
import io.reactivex.Single

class MenuItem {
    public var image: Int = 0
    public var name: String? = null
}

@Entity(tableName = "category")
data class CategoryItem(
        @PrimaryKey var id: Int = 0,
        @ColumnInfo(name = "english")
        var english: String? = "",
        @ColumnInfo(name = "vietnamese")
        var vietnamese: String? = "",
        @ColumnInfo(name = "chinese")
        var chinese: String? = "")


class PhraseExItem {
    var cateId: Int = 0
    var favorite: Int = 0
    /* renamed from: id */
    var f2070id: Int = 0
    var korean: String? = null
    var search: String? = null
    var vietnamese: String? = null
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryItem>
}

@Database(entities = [CategoryItem::class], version = 22)
abstract class MainDB : RoomDatabase() {

    abstract fun CategoryDao(): CategoryDao

    companion object {
        private var INSTANCE: MainDB? = null

        fun getInstance(context: Context): MainDB? {
            if (INSTANCE == null) {
                synchronized(MainDB::class) {
                    INSTANCE = RoomAsset.databaseBuilder(context.applicationContext,
                            MainDB::class.java, "endlesslove.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
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