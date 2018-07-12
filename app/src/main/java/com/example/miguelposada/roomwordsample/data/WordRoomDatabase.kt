package com.example.miguelposada.roomwordsample.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.arch.persistence.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        // singleton
        private var instance: WordRoomDatabase? = null
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(instance!!).execute()
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase {
            if (instance == null) {
                synchronized(WordRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            WordRoomDatabase::class.java,
                            "database_name"
                    ).addCallback(sRoomDatabaseCallback).build()
                }
            }
            return instance!!
        }
    }

    private class PopulateDbAsync internal constructor(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {

        private val mDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
            return null
        }
    }
}