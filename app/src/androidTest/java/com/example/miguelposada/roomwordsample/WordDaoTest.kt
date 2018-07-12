package com.example.miguelposada.roomwordsample

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.miguelposada.roomwordsample.data.Word
import com.example.miguelposada.roomwordsample.data.WordDao
import com.example.miguelposada.roomwordsample.data.WordRoomDatabase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule





/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WordDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mWordDao: WordDao
    lateinit var db: WordRoomDatabase

    @Before
    fun createDb() {
        val context: Context = InstrumentationRegistry.getContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WordRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        mWordDao = db.wordDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetRow() {
        val word = Word(mWord = "Android")
        mWordDao.insert(word)
        val words: List<Word> = LiveDataTestUtil.getValue(mWordDao.getAlphabetizedWords())
        assertEquals(words[0].mWord, word.mWord)
    }

    @Test
    @Throws(Exception::class)
    fun getAllWords() {
        val word = Word("aaa")
        mWordDao.insert(word)
        val word2 = Word("bbb")
        mWordDao.insert(word2)
        val allWords = LiveDataTestUtil.getValue(mWordDao.getAlphabetizedWords())
        assertEquals(allWords[0].mWord, word.mWord)
        assertEquals(allWords[1].mWord, word2.mWord)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() {
        val word = Word("word")
        mWordDao.insert(word)
        val word2 = Word("word2")
        mWordDao.insert(word2)
        mWordDao.deleteAll()
        val allWords = LiveDataTestUtil.getValue(mWordDao.getAlphabetizedWords())
        assertTrue(allWords.isEmpty())
    }
}
