package com.example.miguelposada.roomwordsample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.miguelposada.roomwordsample.data.Word
import com.example.miguelposada.roomwordsample.data.WordDao
import com.example.miguelposada.roomwordsample.data.WordRoomDatabase

class WordRepository {
    companion object {
        var mWordDao: WordDao? = null
        var mAllWords: LiveData<List<Word>>? = null
    }

    constructor(application: Application) {
        val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao?.getAlphabetizedWords()
    }

    // in the constructor its value is assigned
    fun getAllWords(): LiveData<List<Word>> = mAllWords!!

    fun insert(word: Word) {
        mWordDao?.let {
            InsertAsyncTask(it).execute(word)
        }
    }

    /**
     * Basic AsyncTask
     */
    class InsertAsyncTask: AsyncTask<Word, Void, Void> {

        var mAsyncTaskDao: WordDao? = null

        constructor(dao: WordDao) {
            mAsyncTaskDao = dao
        }

        override fun doInBackground(vararg params: Word?): Void? {
            params[0]?.let {
                mAsyncTaskDao?.insert(it)
            }
            return null
        }

    }
}