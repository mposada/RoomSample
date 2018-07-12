package com.example.miguelposada.roomwordsample.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.miguelposada.roomwordsample.WordRepository
import com.example.miguelposada.roomwordsample.data.Word

/**
 * Warning: Never pass context into ViewModel instances.
 * Do not store Activity, Fragment, or View instances or their Context in the ViewModel.
 *
 * For example, an Activity can be destroyed and created many times during the lifecycle
 * of a ViewModel as the device is rotated. If you store a reference to the Activity in
 * the ViewModel, you end up with references that point to the destroyed Activity.
 * This is a memory leak.
 *
 * If you need the application context, use AndroidViewModel, as shown in this class.
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private var mRepository: WordRepository? = null
        private var mAllWords: LiveData<List<Word>>? = null
    }

    init {
        mRepository = WordRepository(application)
        mAllWords = mRepository?.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> = mRepository!!.getAllWords()

    fun insert(word: Word) = mRepository!!.insert(word)
}