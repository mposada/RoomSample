package com.example.miguelposada.roomwordsample

import android.arch.core.executor.ArchTaskExecutor
import android.arch.core.executor.TaskExecutor
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A JUnit Test Rule that swaps the background executor used by the Architecture Components with a
 * different one which executes each task synchronously.
 * You can use this rule for your host side tests that use Architecture Components.
 *
 * @TestWatcher is a base class for Rules that take note of the testing action, without modifying it.
 */
class InstantTaskExecutorRule: TestWatcher() {

    /**
     * Invoked when a test is about to start
     */
    override fun starting(description: Description?) {
        super.starting(description)
        // ArchTaskExecutor is a static class that serves as a central point to execute common tasks.
        ArchTaskExecutor.getInstance().setDelegate(object: TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean = true
        })
    }

    override fun finished(description: Description?) {
        super.finished(description)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}