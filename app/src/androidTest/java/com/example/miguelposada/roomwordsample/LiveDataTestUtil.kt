package com.example.miguelposada.roomwordsample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class LiveDataTestUtil {

    companion object {

        /**
         * Get the value from a LiveData object. We're waiting for LiveData to emit, for 2 seconds.
         * Once we got a notification via onChanged, we stop observing.
         */
        @Throws(InterruptedException::class)
        fun <T> getValue(liveData: LiveData<T>): T {
            val data = arrayOfNulls<Any>(1)
            // A synchronization aid that allows one or more threads to wait until a set of
            // operations being performed in other threads completes.
            val countDownLatch = CountDownLatch(1)
            val observer = object : Observer<T> {
                override fun onChanged(t: T?) {
                    data[0] = t
                    countDownLatch.countDown()
                    liveData.removeObserver(this)
                }
            }

            liveData.observeForever(observer)
            countDownLatch.await(2, TimeUnit.SECONDS)
            return data[0] as T
        }

    }
}