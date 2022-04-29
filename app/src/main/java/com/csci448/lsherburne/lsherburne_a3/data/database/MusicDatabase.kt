package com.csci448.lsherburne.lsherburne_a3.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track


// database built based on the same pattern as seen in the Samodelkin Lab's database
@Database(entities=[Album::class, Track::class], version = 1)
@TypeConverters(MusicTypeConverters::class)
abstract class MusicDatabase : RoomDatabase() {
    // add the abstract DAO value - this goes on the class outside the companion object
    abstract val musicDao: MusicDao

    companion object {
        // follow the singleton pattern
        // name the database "musiic-database" or similar
        @Volatile
        private var INSTANCE: MusicDatabase? = null
        fun getInstance(context: Context): MusicDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        MusicDatabase::class.java,
                        "music-database"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}