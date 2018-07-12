package com.example.miguelposada.roomwordsample.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
abstract class WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(word: Word)

    @Query("DELETE FROM word_table")
    abstract fun deleteAll()

    /**
     * If you use LiveData independently from Room, you have to manage updating the data.
     * LiveData has no publicly available methods to update the stored data.
     *
     * If you, the developer, want to update the stored data, you must use MutableLiveData
     * instead of LiveData. The MutableLiveData class has two public methods that allow
     * you to set the value of a LiveData object, setValue(T) and postValue(T).
     * Usually, MutableLiveData is used in the ViewModel, and then the ViewModel only
     * exposes immutable LiveData objects to the observers.
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    abstract fun getAlphabetizedWords(): LiveData<List<Word>>
}